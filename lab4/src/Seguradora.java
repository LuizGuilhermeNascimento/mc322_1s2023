import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Date;

public class Seguradora {
    
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistro;
    private ArrayList<Cliente> listaClientes;
    
    // construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        listaSinistro = new ArrayList<Sinistro>();
        listaClientes = new ArrayList<Cliente>();
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Cadastro de cliente
     * @return True se o cadastro for concluído com sucesso, False senão
     */
    public boolean cadastrarCliente(Cliente cliente) {
        if (cliente == null) { return false; }
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i) != null && listaClientes.get(i).getDocumento().equals(cliente.getDocumento())) {
                return false;
            }
        }
        cliente.setValorSeguro(calcularPrecoSeguroCliente(cliente));
        this.listaClientes.add(cliente);
        return true;
    }

    /**
     * Remoção de cliente
     * @return True se a remoção for concluído com sucesso, False senão
     */
    public boolean removerCliente(String cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i) != null && listaClientes.get(i).getDocumento().equals(cliente)) {
                listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Listagem de clientes por tipo (PF ou PJ)
     * @return ArrayList<Cliente> com clientes do mesmo tipo
     */
    public ArrayList<Cliente> listarClientesPorTipo(String tipoCliente) {

        ArrayList<Cliente> clientesArray = new ArrayList<>();
        for (Cliente c : listaClientes) {
            if ((tipoCliente.equals("PF") && c instanceof ClientePF) || (tipoCliente.equals("PJ") && c instanceof ClientePJ)) {
                clientesArray.add(c);
            }
        }
        return clientesArray;

    }

    /**
     * Listagem de clientes geral (PF e PJ)
     * @return ArrayList<Cliente> com todos os clientes
     */
    public ArrayList<Cliente> listarClientes() {
        return this.listaClientes;
    }

    /**
     * Geração de sinistro
     * @return True se o registro for concluído com sucesso, False senão
     */
    public boolean gerarSinistro(Date data, String endereco, Veiculo veiculo, Cliente cliente) {
        
        listaSinistro.add(new Sinistro(data,endereco,this,veiculo,cliente));
        for (Cliente c : this.listaClientes) {
            if (c.getDocumento().equals(cliente.getDocumento())) {
                c.setValorSeguro(calcularPrecoSeguroCliente(c));
            }
        }
        return true;
    }

    /**
     * Visualização do Sinistro de um Cliente
     * @return True se o cliente possui um sinistro, False senão
     */
    public String visualizarSinistro(String cliente) {
        StringBuilder sb = new StringBuilder();
        for (Sinistro s : listaSinistro) {
            if (s.getCliente().getDocumento().equals(cliente)) {
                sb.append(s.toString());
            }
        }
        if (!sb.isEmpty()) {
            return sb.toString();
        }
        return "\nO cliente não possui sinistros!\n";
    }

    /**
     * Listagem de Sinistro
     * @return ArrayList<Sinistro>
     */
    public ArrayList<Sinistro> listarSinistros() {
        return listaSinistro;
    }

    /**
     * Excluir sinistro com base no seu ID
     * @return True se o sinistro for encontrado, False senão
     */
    public boolean excluirSinistro(int id) {
        int indice_remover = 0;
        boolean sinistro_encontrado = false;
        for (int i = 0; i < listaSinistro.size(); i++) {
            if (listaSinistro.get(i).getId() == id) {
                indice_remover = i;
                sinistro_encontrado = true;
                break;
            }
        }
        listaSinistro.remove(indice_remover);
        return sinistro_encontrado;
    }
    
    /**
     * Transforma uma ArrayList de clientes em uma String
     * @return String
     */
    public String toStringListaClientes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaClientes.size(); i++) {
            sb.append("\n----- Cliente "+(i+1)+" ----\n");
            sb.append(listaClientes.get(i).toString()+"\n");
        }
        return sb.toString();
    }
    
    /**
     * Transforma uma ArrayList de sinistros em uma String
     * @return String
     */
    public String toStringListaSinistro() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaSinistro.size(); i++) {
            sb.append("\n----- Sinistro "+(i+1)+" ----\n");
            sb.append(listaSinistro.get(i).toString()+"\n");
        }
        return sb.toString();
    }

    /**
     * Procura um cliente baseado em um documento (CPF OU CNPJ)
     * @return objeto Cliente, ou null caso não for encontrado
     */
    public Cliente procurarClientePorDocumento(String tipoCliente) {
        for (Cliente c : listaClientes) {
            if (tipoCliente.equals(c.getDocumento())) {
                return c;
            }
        }
        return null;
    }
    
    public String toString() {
        return "Nome: "+this.nome+"\nTelefone: "+this.telefone+"\nEmail: "+this.email+"\nEndereço: "+this.endereco+
        "\n\nLista de Clientes: "+toStringListaClientes()+"\nLista de Sinistros: "+toStringListaSinistro();
    }

    /**
     * Calcula o preço do seguro de um cliente utilizando seu score
     * e a quantidade de sinistros
     */
    public double calcularPrecoSeguroCliente(Cliente cliente) {
        int qtdeSinistros = 0;
        for (Sinistro s : listaSinistro) {
            if (s.getCliente().getDocumento().equals(cliente.getDocumento())) {
                qtdeSinistros++;
            }
        }
        return cliente.calculaScore() * (1+qtdeSinistros);
    }

    /**
     * Transferir seguro
     * @return True se a operação foi realizada com sucesso, False senão
     */
    public boolean transferirSeguro(String clienteFonteDocumento, String clienteDestinoDocumento) {
        ArrayList<Veiculo> fonteArray = procurarClientePorDocumento(clienteFonteDocumento).listarVeiculos();
        boolean fonteAtualizada = false;
        boolean destinoAtualizado = false;
        for (Cliente c : listaClientes) {
            if (c.getDocumento().equals(clienteFonteDocumento)) {
                c.setListaVeiculos(new ArrayList<Veiculo>());
                fonteAtualizada = true;
            } else if (c.getDocumento().equals(clienteDestinoDocumento)) {
                c.setListaVeiculos(fonteArray);
                destinoAtualizado = true;
            }
            if (fonteAtualizada && destinoAtualizado) { return true; }
        }
        return false;
    }

    /**
     * Calcula a receita da seguradora
     */
    public double calcularReceita() {
        double receita = 0;
        for (Cliente c : this.listaClientes) {
            receita += c.getValorSeguro(); 
        }
        return receita;
    }
}

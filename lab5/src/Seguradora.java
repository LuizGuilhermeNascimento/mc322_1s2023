import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Date;
import java.util.HashMap;

public class Seguradora {
    
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Seguro> listaSeguros;
    private ArrayList<Cliente> listaClientes;
    
    // construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        listaSeguros = new ArrayList<Seguro>();
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


    public boolean gerarSeguro(Date dataInicio, Date dataFim, Veiculo veiculo, ClientePF cliente) {
        Seguro novoSeguro = new SeguroPF(dataInicio, dataFim, this, veiculo, cliente);
        return listaSeguros.add(novoSeguro);
    }

    public boolean gerarSeguro(Date dataInicio, Date dataFim, Frota frota, ClientePJ cliente) {
        Seguro novoSeguro = new SeguroPJ(dataInicio, dataFim, this, frota, cliente);
        return listaSeguros.add(novoSeguro);
    }

    public boolean cancelarSeguro(String documentoCliente) {
        for (int i = 0; i < listaSeguros.size(); i++) {
            if (listaSeguros.get(i).getCliente() instanceof ClientePF) {
                ClientePF clientePF = (ClientePF)listaSeguros.get(i).getCliente();
                if (clientePF.getVeiculoPorPlaca(identificador) != null) {
                    return listaSeguros.remove(i);
                }
            }
            if (listaSeguros.get(i).getCliente() instanceof ClientePJ) {
                ClientePJ clientePJ = (ClientePJ)listaSeguros.get(i).getCliente();
                if (clientePJ.getVeiculosPorFrota(identificador) != null) {
                    return listaSeguros.remove(i);
                }
            }
        }
        return false;
    }


    public ArrayList<Seguro> getSegurosPorCliente(String documentoCliente) {
        ArrayList<Seguro> seguros = new ArrayList<>();
        for (Seguro s : listaSeguros) {
            if (s.getCliente().getDocumento().equals(documentoCliente)) {
                seguros.add(s);
            }
        }
        return seguros;
    }

    public ArrayList<Sinistro> getSinistrosPorCliente(String documentoCliente) {
        ArrayList<Sinistro> sinistros = new ArrayList<>();
        for (Seguro s : listaSeguros) {
            if (s.getCliente().getDocumento().equals(documentoCliente)) {
                for (Sinistro sinistro : s.getListaSinistros()) {
                    sinistros.add(sinistro);
                }
            }
        }
        return seguros;
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
     * Calcula a receita da seguradora
     */
    public double calcularReceita() {
        double receita = 0;
        for (Seguro s : listaSeguros) {
            receita += s.calculaValor(); 
        }
        return receita;
    }
}

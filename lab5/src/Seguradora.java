import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Date;

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

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public boolean gerarSeguro(Date dataInicio, Date dataFim, Veiculo veiculo, ClientePF cliente) {
        Seguro novoSeguro = new SeguroPF(dataInicio, dataFim, this, veiculo, cliente);
        return listaSeguros.add(novoSeguro);
    }

    public boolean gerarSeguro(Date dataInicio, Date dataFim, Frota frota, ClientePJ cliente) {
        Seguro novoSeguro = new SeguroPJ(dataInicio, dataFim, this, frota, cliente);
        return listaSeguros.add(novoSeguro);
    }
    public boolean gerarSeguro(Seguro seguro) {
        return listaSeguros.add(seguro);
    }

    public boolean cancelarSeguro(String documentoCliente, String identificador) {
        for (int i = 0; i < listaSeguros.size(); i++) {
            if (listaSeguros.get(i).getCliente() instanceof ClientePF) {
                ClientePF clientePF = (ClientePF)listaSeguros.get(i).getCliente();
                if (clientePF.getVeiculoPorPlaca(identificador) != null) {
                    listaSeguros.remove(i);
                    return true;
                }
            }
            if (listaSeguros.get(i).getCliente() instanceof ClientePJ) {
                ClientePJ clientePJ = (ClientePJ)listaSeguros.get(i).getCliente();
                if (clientePJ.getVeiculosPorFrota(identificador) != null) {
                    listaSeguros.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean cancelarSeguroPorId(int id) {
        for (int i = 0; i < listaSeguros.size(); i++) {
            if (listaSeguros.get(i).getId() == id) {
                listaSeguros.remove(i);
                return true;
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
        return sinistros;
    }

    public ArrayList<Sinistro> getSinistrosSeguradora() {
        ArrayList<Sinistro> sinistros = new ArrayList<>();
        for (Seguro s : listaSeguros) {
            sinistros.addAll(s.getListaSinistros());
        }
        return sinistros;
    }

    public ArrayList<Veiculo> getVeiculosSeguradora() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        for (Cliente c : listaClientes) {
            veiculos.addAll(c.listaVeiculosCadastrados());
        }
        return veiculos;
    }

    public ArrayList<Frota> getFrotaSeguradora() {
        ArrayList<Frota> frotas = new ArrayList<>();

        for (Cliente c : listaClientes) {
            if (c instanceof ClientePJ) {
                ClientePJ clientePJ = (ClientePJ)c;
                frotas.addAll(clientePJ.listarFrotas());
            }
        }
        return frotas;
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
        "\n\nLista de Clientes: "+toStringListaClientes();
    }

    /**
     * Calcula o preço do seguro de um cliente utilizando seu score
     * e a quantidade de sinistros
     */
    public double calcularPrecoSeguroCliente(String documentoCliente) {
        double precoSeguro = 0;
        for (Seguro s : listaSeguros) {
            if (s.getCliente().getDocumento().equals(documentoCliente)) {
                precoSeguro += s.calculaValor();
            }
        }
        return precoSeguro;
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

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Seguradora implements I_Arquivo{
    
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Seguro> listaSeguros;
    private ArrayList<Cliente> listaClientes;
    
    // construtor
    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco) {
        listaSeguros = new ArrayList<Seguro>();
        listaClientes = new ArrayList<Cliente>();

        if (Validacao.validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else { this.cnpj = "CNPJ inválido!"; }

        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public String getCNPJ() {
        return cnpj;
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

    /**
     * 1º sobrecarga de gerarSeguro
     * Gera um seguro PF recebendo os dados separadamente,
     * adicionando o seguro na lista de seguros
     */
    public boolean gerarSeguro(Date dataInicio, Date dataFim, Veiculo veiculo, ClientePF cliente) {
        Seguro novoSeguro = new SeguroPF(dataInicio, dataFim, this, veiculo, cliente);
        return listaSeguros.add(novoSeguro);
    }

    /**
     * 2º sobrecarga de gerarSeguro
     * Gera um seguro PJ recebendo os dados separadamente,
     * adicionando o seguro na lista de seguros
     */
    public boolean gerarSeguro(Date dataInicio, Date dataFim, Frota frota, ClientePJ cliente) {
        Seguro novoSeguro = new SeguroPJ(dataInicio, dataFim, this, frota, cliente);
        return listaSeguros.add(novoSeguro);
    }

    /**
     * 3º sobrecarga de gerarSeguro
     * Recebe o objeto Seguro e o adiciona na lista de seguros
     */
    public boolean gerarSeguro(Seguro seguro) {
        return listaSeguros.add(seguro);
    }

    /**
     * Cancela o seuro
     * @param documentoCliente
     * @param identificador
     * O identificador pode representar a placa do veículo ou o code da frota
     * Se o cliente for PF, então o identificador representa a placa do veículo
     * cadastrado no cliente
     * Se o cliente for PJ, então o identificador representa o code da frota
     * cadastrada no cliente
     */
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

    /**
     * Cancela o seguro baseado no seu id
     */
    public boolean cancelarSeguroPorId(int id) {
        for (int i = 0; i < listaSeguros.size(); i++) {
            if (listaSeguros.get(i).getId() == id) {
                listaSeguros.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna um array com os seguros de um cliente específico
     */
    public ArrayList<Seguro> getSegurosPorCliente(String documentoCliente) {
        ArrayList<Seguro> seguros = new ArrayList<>();
        for (Seguro s : listaSeguros) {
            if (s.getCliente().getDocumento().equals(documentoCliente)) {
                seguros.add(s);
            }
        }
        return seguros;
    }

    /**
     * Retorna um array com os sinistros de um cliente específico
     */
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

    /**
     * Retorna um array com todos os sinistros da seguradora
     */
    public ArrayList<Sinistro> getSinistrosSeguradora() {
        ArrayList<Sinistro> sinistros = new ArrayList<>();
        for (Seguro s : listaSeguros) {
            sinistros.addAll(s.getListaSinistros());
        }
        return sinistros;
    }

    /**
     * Retorna um array com todos os veículos da seguradora
     */
    public ArrayList<Veiculo> getVeiculosSeguradora() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        for (Cliente c : listaClientes) {
            veiculos.addAll(c.listaVeiculosCadastrados());
        }
        return veiculos;
    }

    /**
     * Retorna um array com todas as frotas da seguradora
     */
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

    public boolean gravarArquivo(String filePath, Seguro seguro) {

        File file = new File(filePath);
        
        if (!file.isFile()) {
            System.out.println("Não é um arquivo!");
            return false;
        }
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
                long id = reader.lines().count();
                
                StringBuilder listaSinistros = new StringBuilder();
                
                for (int i = 0; i < seguro.getListaSinistros().size(); i++) {
                    listaSinistros.append(seguro.getListaSinistros().get(i).getId());
                    if (i < seguro.getListaSinistros().size()-1) {
                        listaSinistros.append(",");
                    }
                }

                StringBuilder listaCondutores = new StringBuilder();

                for (int i = 0; i < seguro.getListaCondutores().size(); i++) {
                    listaCondutores.append(seguro.getListaCondutores().get(i).getCPF());
                    if (i < seguro.getListaCondutores().size()-1) {
                        listaCondutores.append(",");
                    }
                }

                SimpleDateFormat sdf = new SimpleDateFormat();
                String dataInicio = sdf.format(seguro.getDataInicio()).split(" ")[0];
                String dataFim = sdf.format(seguro.getDataFim()).split(" ")[0];
                String newLine = String.format("%d,%s,%s,%s,%s,%s,%f\n", id, dataInicio, dataFim,
                seguro.getSeguradora().getNome(), listaSinistros.toString(), listaCondutores.toString(), seguro.getValorMensal());

                writer.write(newLine);
                writer.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return true;
    }

    public boolean inicializarArquivo(String filePath, String header) {

        File file = new File(filePath);

        if (file.exists()) {
            return false;  
        }
        System.out.println("O arquivo não existe!");
        try {
            boolean arquivoCriado = file.createNewFile();
            if (!arquivoCriado) { 
                System.out.println("O arquivo não foi possível de ser criado");
                return false; 
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            
            writer.write(header);
            writer.newLine();
            writer.close();

        } catch(IOException ex) {
            return false;
        }
        return true;
    }

    public String lerArquivo() {
        return "";
    }
}

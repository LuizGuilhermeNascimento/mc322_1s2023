import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ClientePF extends Cliente {

    private String educacao;
    private String genero;
    private final String cpf;
    private Date dataNascimento;
    private String telefone;
    private String email;
    private String sexo;
    private ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome , String endereco ,
            String educacao , String genero, String cpf, Date dataNascimento,
            String telefone, String email) {

        super( nome , endereco);
        if (Validacao.validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            this.cpf = "CPF inválido";
        }
        this.educacao = educacao;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = new ArrayList<Veiculo>();
        this.telefone = telefone;
        this.email = email;
    }

    // getters e setters
    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int calcularIdade(Date dataNascimento) {
        Date data_atual = new Date();
        // calculando a diferença entre as datas em milissegundos
        long diferencaMS = data_atual.getTime() - dataNascimento.getTime();
        // converte-se a diferença de milissegundos em minutos
        long diff = TimeUnit.MINUTES.convert(diferencaMS, TimeUnit.MILLISECONDS);
        // retorna a diferença convertida em anos
        return (int)(diff / 525600);
    }

     /**
     * Transforma uma ArrayList de clientes em String
     * @return String contendo as informações dos veículos do cliente
     */
    public String listaVeiculosToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nLista de veículos:");
        int i = 1;
        for (Veiculo c : this.listaVeiculos) {
            sb.append("\n--- Veículo "+(i)+" ---");
            sb.append("\n" + c.toString());
        }
        return sb.toString();
    }

    /**
     * Cadastra um veículo no cliente
     * @return True se o cadastro for concluído com sucesso, False senão
     */
    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (veiculo == null) { return false; }
        listaVeiculos.add(veiculo);
        return true;
    }

    /**
     * Remove um veículo cadastrado no veículo
     * @return True se a remoção for concluída com sucesso, False senão
     */
    public boolean removerVeiculo(String placaVeiculo) {
        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (listaVeiculos.get(i) != null && listaVeiculos.get(i).getPlaca().equals(placaVeiculo)) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Listagem dos veículos
     * @return
     */
    public ArrayList<Veiculo> listarVeiculos() {
        return this.listaVeiculos;
    }

    /**
     * Retorna um veículo baseado na placa
     */
    public Veiculo getVeiculoPorPlaca(String placa) {
        for (Veiculo v : listaVeiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Veiculo> listaVeiculosCadastrados() {
        return listaVeiculos;
    }
    
    /**
     * Retorna o documento do cliente
     */
    @Override
    public String getDocumento() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nEndereço: " + this.endereco+"\nEducação: " + this.educacao + "\nGênero: " + this.genero+
        "\nCPF: "+this.cpf+"\nData de nascimento: "+this.dataNascimento+listaVeiculosToString();
    }

}

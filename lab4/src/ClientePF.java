import java.util.Date;

public class ClientePF extends Cliente{
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private final String cpf;
    private Date dataNascimento;

    public ClientePF(String nome , String endereco , Date dataLicenca,
            String educacao , String genero , String classeEconomica,
            String cpf, Date dataNascimento) {

        super( nome , endereco);
        if (Validacao.validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            this.cpf = "CPF inválido";
        }
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
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
        return "Nome: " + this.nome + "\nEndereço: " + this.endereco + "\nData de Licença: " + this.dataLicenca.toString() +
        "\nEducação: " + this.educacao + "\nGênero: " + this.genero+ "\nClasse Econômica: " + this.classeEconomica+"\nCPF: "+
        this.cpf+"\nData de nascimento: "+this.dataNascimento+listaVeiculosToString();
    }

    public double calculaScore();
}

import java.util.Date;

public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;


    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao) {

        super( nome , endereco);
        if (Validacao.validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else {
            this.cnpj = "CNPJ inválido";
        }
        this.dataFundacao = dataFundacao;
    }

    public String getCNPJ() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataNascimento(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return "Nome: "+this.nome +"\nEndereço: "+this.endereco +"\nCNPJ: "+this.cnpj+"\nData de fundação: "+this.dataFundacao + listaVeiculosToString();
    }

    /**
     * Retorna o documento do cliente
     */
    @Override
    public String getDocumento() {
        return this.cnpj;
    }

    public double calculaScore();
}

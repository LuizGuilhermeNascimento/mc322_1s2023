import java.util.Date;

public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;
    private int qtdeFuncionarios;


    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, int qtdeFuncionarios) {

        super( nome , endereco);
        if (Validacao.validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else {
            this.cnpj = "CNPJ inválido";
        }
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
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
        return "Nome: "+this.nome +"\nEndereço: "+this.endereco +"\nCNPJ: "+this.cnpj+"\nData de fundação: "+this.dataFundacao +
        "\nValor do Seguro: "+this.valorSeguro+"\nQuantidade de funcionários: "+this.qtdeFuncionarios+ listaVeiculosToString();
    }

    /**
     * Retorna o documento do cliente
     */
    @Override
    public String getDocumento() {
        return this.cnpj;
    }

    /**
     * Calcula o score do cliente baseado na fórmula fornecida
     */
    @Override
    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.getValue() * (1+ (double)(this.qtdeFuncionarios)/100) * this.listaVeiculos.size();
    };
}

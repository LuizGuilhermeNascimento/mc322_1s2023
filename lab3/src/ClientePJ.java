import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;

    public ClientePJ(String nome , String endereco , Date dataLicenca,
            String educacao , String genero , String classeEconomica,
            List< Veiculo > listaVeiculos, String cnpj, Date dataFundacao) {

        super( nome , endereco , dataLicenca , educacao , genero , classeEconomica , listaVeiculos );
        if (validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else {
            this.cnpj = "CNPJ inválido!";
        }
        this.dataFundacao = dataFundacao;
    }

    public ClientePJ(String nome , String endereco , Date dataLicenca,
            String educacao , String genero , String classeEconomica,
            String cnpj, Date dataFundacao) {

        super( nome , endereco , dataLicenca , educacao , genero , classeEconomica);
        if (validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else {
            this.cnpj = "CNPJ inválido!";
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
        return "CNPJ: "+this.cnpj+"\nData de fundação: "+this.dataFundacao+toString();
    }
}

import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;

    public ClientePJ(String nome , String endereco , Date dataLicenca,
            String educacao , String genero , String classeEconomica,
            List< Veiculo > listaVeiculos, String cnpj, Date dataFundacao) {

        super( nome , endereco , listaVeiculos );
        if (validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else {
            this.cnpj = "CNPJ inválido";
        }
        this.dataFundacao = dataFundacao;
    }

    public ClientePJ(String nome , String endereco , Date dataLicenca,
            String educacao , String genero , String classeEconomica,
            String cnpj, Date dataFundacao) {

        super( nome , endereco );
        if (validarCNPJ(cnpj)) {
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
        return "CNPJ: "+this.cnpj+"\nData de fundação: "+this.dataFundacao + listaVeiculosToString();
    }

    public static boolean validarCNPJ(String cnpj) {

        String regex = "[^0-9]";
        cnpj = cnpj.replaceAll(regex, "");
        int[] seqVerificacao = {6,5,4,3,2,9,8,7,6,5,4,3,2};
        int acc = 0;

        //cálculo do primeiro dígito verificador
        for (int i = 1; i < seqVerificacao.length; i++) {
            acc += seqVerificacao[i] * Integer.parseInt(cnpj.substring(i-1, i));
        }
        int restoDiv = (acc % 11);
        int primeiroDig = 0;
        if (restoDiv >= 2) { primeiroDig = 11 - restoDiv; }
        if (Integer.parseInt(cnpj.substring(12,13)) != primeiroDig) {
            return false;
        }

        //cálculo do segundo dígito verificador
        acc = 0;
        for (int i = 0; i < seqVerificacao.length; i++) {
            acc += seqVerificacao[i] * Integer.parseInt(cnpj.substring(i, i+1));
        }
        restoDiv = (acc % 11);
        int segundoDig = 0;
        if (restoDiv >= 2) { segundoDig = 11 - restoDiv; }
        if (Integer.parseInt(cnpj.substring(13)) != segundoDig) {
            return false;
        }
        return true;
    }

    @Override
    public String getDocumento() {
        return this.cnpj;
    }
}

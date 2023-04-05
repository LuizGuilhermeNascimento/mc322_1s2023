import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente{
    private final String cpf;
    private Date dataNascimento;

    public ClientePF(String nome , String endereco , Date dataLicenca,
            String educacao , String genero , String classeEconomica,
            List < Veiculo > listaVeiculos, String cpf, Date dataNascimento) {

        super( nome , endereco , dataLicenca , educacao , genero , classeEconomica , listaVeiculos );
        if (validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            this.cpf = "CPF inválido!";
        }
        this.dataNascimento = dataNascimento;
    }

    public ClientePF(String nome , String endereco , Date dataLicenca,
            String educacao , String genero , String classeEconomica,
            String cpf, Date dataNascimento) {

        super( nome , endereco , dataLicenca , educacao , genero , classeEconomica);
        if (validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            this.cpf = "CPF inválido!";
        }
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
    
    // recebe um cpf na forma de String e calcula seus dígitos verificadores
    private int calcularDigitosVerificadores(String c) {

        int dig1 = 0;
        int dig2 = 0;
        int acc = 0;

        // cálculo do primeiro dígito
        for (int i = 0; i < 9; i++) {
            int dig_cpf = Integer.parseInt(c.substring(i,i+1));
            acc += dig_cpf*(10-i);
        }
        dig1 = 11 - (acc % 11);

        // cálculo do segundo dígito
        acc = 0;
        for (int i = 0; i < 10; i++) {
            int dig_cpf = Integer.parseInt(c.substring(i,i+1));
            acc += dig_cpf*(11-i);
        }
        dig2 = 11 - (acc % 11);
        if (dig1 >= 10) { dig1 = 0; }
        if (dig2 >= 10) { dig2 = 0; }

        return (dig1*10)+dig2;
    }

    // recebe um cpf e retorna um booleano indicando se o CPF é válido
    public boolean validarCPF(String c) {

        String regex = "[^0-9]";
        c = c.replaceAll(regex, "");

        // verifica se o cpf possui 11 dígitos
        if (c.length() != 11) {
            return false;
        }
        // verifica se todos os caracteres são iguais
        boolean allEquals = true;
        for (int i = 1; i < c.length(); i++) {
            if (c.charAt(i) != c.charAt(0)) {
                allEquals = false;
            }
        }
        if (allEquals) { return false; }

        // verifica se os dígitos verificadores estão corretos
        int digitosVerif = calcularDigitosVerificadores(c);
        if (Integer.parseInt(c.substring(9)) != digitosVerif) {
            return false;
        }

        return true;
    }
    
    @Override
    public String toString() {
        return "CPF: "+this.cpf+"\nData de nascimento: "+this.dataNascimento+toString();
    }
}

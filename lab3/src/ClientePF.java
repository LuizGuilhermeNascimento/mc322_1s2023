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
        if (validarCPF(cpf)) {
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
     * Cálculo dos dígitos verificadores
     * @return O dígito verificador obtido a partir do cpf recebido
     */
    private static int calcularDigitosVerificadores(String c) {

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

    /**
     * Validação de CPF
     * @return True se o CPF for válido, False senão
     */
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
}

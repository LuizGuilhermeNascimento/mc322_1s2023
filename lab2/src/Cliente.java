public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private int endereco;

    public Cliente(String nome, String cpf, String dataNascimento, int idade, int endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public int getIdade() {
        return idade;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }
    
    private int calcularDigitosVerificadores(String c) {
        int dig1 = 0;
        int dig2 = 0;
        int acc = 0;

        for (int i = 0; i < 10; i++) {
            int dig_cpf = Integer.parseInt(c.substring(i));
            acc += dig_cpf*(10-i);
        }
        dig1 = 11 - (acc % 11);
        acc = 0;
        for (int i = 0; i < 10; i++) {
            int dig_cpf = Integer.parseInt(c.substring(i));
            acc += dig_cpf*(11-i);
        }

        return (dig1*10)+dig2;
    }

    private boolean validarCPF() {

        String c = this.cpf;
        String regex = "/./-";
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

        int digitosVerif = calcularDigitosVerificadores(c);

        if (Integer.parseInt(c.substring(9)) != digitosVerif) {
            return false;
        }

        return true;
    }
}

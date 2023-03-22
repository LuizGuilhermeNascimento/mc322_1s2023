public class Cliente {
    
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // construtor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        if (validarCPF(cpf)) {
            this.cpf = cpf;
        }
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    // getters e setters
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

    public String getEndereco() {
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

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public String toString() {
        String clienteString = "Nome: " + this.nome + "\nCPF: " + this.cpf + "\nData de Nascimento: " + this.dataNascimento +
                                "\nIdade: " + this.idade + "\nEndereço: " + this.endereco;
        return clienteString;
    }
}

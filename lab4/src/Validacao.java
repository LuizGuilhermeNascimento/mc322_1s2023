public static class Validacao {
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
     * Validação de CNPJ
     * @return True se o CNPJ for válido, False senão
     */
    public boolean validarCNPJ(String cnpj) {

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

    public boolean validarNome(String nome);
}

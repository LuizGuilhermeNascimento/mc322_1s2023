public enum CalcSeguro {
    VALOR_BASE (100.0),
    FATOR_18_30 (1.2),
    FATOR_30_60 (1.0),
    FATOR_60_90 (1.5),
    FATOR_IDADE(0);

    private final double value;

    private CalcSeguro(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    /**
     * Calcula o fator idade
     */
    public double calcularFator(int idade) {
        if (idade >= 18 &&  idade < 30) {
            return FATOR_18_30.getValue();
        } else if (idade >= 30 && idade < 60) {
            return FATOR_30_60.getValue();
        }
        return FATOR_60_90.getValue();
    }
}

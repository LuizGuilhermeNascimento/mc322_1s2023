public enum FrotaOperacoes {
    ADICIONAR_VEICULO(0),
    REMOVER_VEICULO(1),
    REMOVER_FROTA(2);

    private int operacao;
    private FrotaOperacoes(int operacao) {
        this.operacao = operacao;
    }
}

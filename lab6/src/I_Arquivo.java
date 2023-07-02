public interface I_Arquivo<T> {
    public boolean gravarArquivo(T t);
    public String lerArquivo(String identificador);
    public String converterDadosParaString(T t);
}

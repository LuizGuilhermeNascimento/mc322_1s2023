public interface I_Arquivo {
    public boolean inicializarArquivo(String filePath, String header);
    public boolean gravarArquivo(String filePath, Seguro seguro);
    public String lerArquivo();
}

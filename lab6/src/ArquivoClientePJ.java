import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ArquivoClientePJ implements I_Arquivo<ClientePJ>{

    private static String filePath = "lab6/src/arquivos-lab6/clientesPJ.csv";

    public boolean gravarArquivo(ClientePJ clientePJ) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String cliente = converterDadosParaString(clientePJ);

            writer.write(cliente);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os Dados");
            return false;
        }
        return true;
    }

    public String lerArquivo(String cnpj) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int i = 0;
            while ((linha = reader.readLine()) != null) {
                if (i >= 1) {
                    String[] campos = linha.split(",");
                    if (campos[0].equals(cnpj)) {
                        return linha;
                    }
                }
                i++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao iterar pelas linhas do arquivo: " + e.getMessage());
        }
        
        return "[NÃO-ENCONTRADO]";
    };

    public String converterDadosParaString(ClientePJ clientePJ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String codeFrota = "";
        if (clientePJ.listarFrotas().size() > 0) {
            codeFrota = clientePJ.listarFrotas().get(0).getCode();
        }

        String dataFundacao = sdf.format(clientePJ.getDataFundacao());
        return clientePJ.getCNPJ() + "," + clientePJ.getNome() + "," + clientePJ.getTelefone() + "," +
        clientePJ.getEndereco() + "," + clientePJ.getEmail() + "," + dataFundacao + "," + 
        codeFrota;
    }
}

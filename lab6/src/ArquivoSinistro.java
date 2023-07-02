import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ArquivoSinistro implements I_Arquivo<Sinistro>{

    private static String filePath = "lab6/src/arquivos-lab6/sinistros.csv";

    public void criarArquivo() {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String header = "ID_SINISTRO,DATA,ENDERECO,CONDUTOR,SEGURO";
            writer.write(header);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os Dados");
        }
    }

    public boolean gravarArquivo(Sinistro sinistro) {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            if (!fileExists) {
                criarArquivo();
            }

            String sinistroString = converterDadosParaString(sinistro);
            sinistroString = getNewId() + "," + sinistroString;
            writer.write(sinistroString);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os Dados");
            return false;
        }
        return true;
    }
    public String lerArquivo(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int i = 0;
            while ((linha = reader.readLine()) != null) {
                if (i >= 1) {
                    String[] campos = linha.split(",");
                    if (campos[0].equals(id)) {
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

    public String converterDadosParaString(Sinistro sinistro) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String data = sdf.format(sinistro.getData());

        return data + "," + sinistro.getEndereco() + "," + sinistro.getCondutor().getCPF() + "," + sinistro.getSeguro().getId();
    }

    public String getNewId() {
        int numeroLinhas = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                numeroLinhas++;
            }
            DecimalFormat decimalFormat = new DecimalFormat("000");
            String numeroComZero = decimalFormat.format(numeroLinhas);
            reader.close();
            return numeroComZero;
            
        } catch (IOException e) {
            System.out.println("Ocorreu um erro ao iterar pelas linhas do arquivo: " + e.getMessage());
        }
        
        return "000";
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class ArquivoSeguro implements I_Arquivo<Seguro>{

    private static String filePath = "lab6/src/arquivos-lab6/seguros.csv";

    public void criarArquivo() {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String header = "ID_SEGURO,DATA_INICIO,DATA_FIM,SEGURADORA,LISTA_SINISTROS,LISTA_CONDUTORES,VALOR_MENSAL";
            writer.write(header);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os Dados");
        }
    }

    public boolean gravarArquivo(Seguro seguro) {
        File file = new File(filePath);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            if (!fileExists) {
                criarArquivo();
            }

            String seguroString = converterDadosParaString(seguro);
            seguroString = getNewId() + "," + seguroString;
            writer.write(seguroString);
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

    public String converterDadosParaString(Seguro seguro) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String dataInicio = sdf.format(seguro.getDataInicio());
        String dataFim = sdf.format(seguro.getDataFim());

        return dataInicio + "," + dataFim + "," + seguro.getSeguradora().getNome() + "," +
        "" + "," + "" + "," + seguro.getValorMensal();
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

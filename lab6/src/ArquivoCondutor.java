import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ArquivoCondutor implements I_Arquivo<Condutor>{

    private static String filePath = "lab6/src/arquivos-lab6/condutores.csv";

    public boolean gravarArquivo(Condutor condutor) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String condutorString = converterDadosParaString(condutor);
            writer.write(condutorString);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os Dados");
            return false;
        }
        return true;
    }

    public String lerArquivo(String cpf) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int i = 0;
            while ((linha = reader.readLine()) != null) {
                if (i >= 1) {
                    String[] campos = linha.split(",");
                    String regex = "[^0-9]";

                    if (campos[0].equals(cpf.replaceAll(regex, ""))) {
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

    public String converterDadosParaString(Condutor condutor) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String regex = "[^0-9]";

        String dataNascimento = sdf.format(condutor.getDataNascimento());
        return condutor.getCPF().replaceAll(regex, "") + "," + condutor.getNome() + "," + condutor.getTelefone() + "," +
        condutor.getEndereco() + "," + condutor.getEmail() + "," + dataNascimento;
    }
}

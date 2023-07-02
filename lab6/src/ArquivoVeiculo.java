import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ArquivoVeiculo implements I_Arquivo<Veiculo> {

    private static String filePath = "lab6/src/arquivos-lab6/veiculos.csv";

    public boolean gravarArquivo(Veiculo veiculo) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String veiculoString = converterDadosParaString(veiculo);
            writer.write(veiculoString);
            writer.newLine();
            writer.close();
        } catch(Exception e) {
            System.out.println("Não foi possível gravar os Dados");
            return false;
        }
        return true;
    }
    public String lerArquivo(String placa) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            int i = 0;
            while ((linha = reader.readLine()) != null) {
                if (i >= 1) {
                    String[] campos = linha.split(",");
                    if (campos[0].equals(placa)) {
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

    public String converterDadosParaString(Veiculo veiculo) {

        return veiculo.getPlaca() + "," + veiculo.getMarca() + "," + veiculo.getModelo() + "," + veiculo.getAnoFabricacao();
    }
}

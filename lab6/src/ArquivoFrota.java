import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class ArquivoFrota implements I_Arquivo<Frota>{

    private static String filePath = "lab6/src/arquivos-lab6/frotas.csv";

    public boolean gravarArquivo(Frota frota) {
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {

            String frotaString = converterDadosParaString(frota);
            frotaString = getNewId() + "," + frotaString;
            writer.write(frotaString);
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

    public String converterDadosParaString(Frota frota) {
        StringBuilder sb = new StringBuilder();

        int tamanhoFrota = frota.getListaVeiculos().size();

        if (tamanhoFrota == 1) {
            sb.append(frota.getListaVeiculos().get(0).getPlaca()+","+""+","+"");
        }
        if (tamanhoFrota == 2) {
            sb.append(frota.getListaVeiculos().get(0).getPlaca()+","+frota.getListaVeiculos().get(1).getPlaca()+","+"");
        }
        if (tamanhoFrota >= 3) {
            sb.append(frota.getListaVeiculos().get(0).getPlaca()+","+frota.getListaVeiculos().get(1).getPlaca()
            +","+frota.getListaVeiculos().get(2).getPlaca());
        }
        return sb.toString();
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

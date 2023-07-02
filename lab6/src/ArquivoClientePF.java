import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class ArquivoClientePF implements I_Arquivo<ClientePF>{

    private static String filePath = "lab6/src/arquivos-lab6/clientesPF.csv";

    public boolean gravarArquivo(ClientePF clientePF) {
        File file = new File(filePath);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
        
            String cliente = converterDadosParaString(clientePF);

            writer.write(cliente);
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

    public String converterDadosParaString(ClientePF clientePF) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String regex = "[^0-9]";

        String dataNascimento = sdf.format(clientePF.getDataNascimento());
        String veiculoCliente = "";
        if (clientePF.listaVeiculosCadastrados().size() > 0) {
            veiculoCliente = clientePF.listaVeiculosCadastrados().get(0).getPlaca();
        }

        return clientePF.getCpf().replaceAll(regex, "") + "," + clientePF.getNome() + "," + clientePF.getTelefone() + "," +
        clientePF.getEndereco() + "," + clientePF.getEmail() + "," + clientePF.getSexo() + "," + clientePF.getEducacao() + "," +
        dataNascimento + "," + veiculoCliente;
    }
}

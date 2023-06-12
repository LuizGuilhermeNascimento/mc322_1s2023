import java.text.SimpleDateFormat;
import java.util.Date;

public class Teste {
    public static void main(String[] args) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataNascimentoCliente = sdf.parse("10/01/2005");
        Date dataInicio = sdf.parse("03/12/2020");
        Date dataFim = sdf.parse("01/01/2024");

        Veiculo veiculo1 = new Veiculo("ABC1D23","Marca1","Modelo1" , 2023);
        ClientePF clientePF = new ClientePF("nome clientePF", "endereço clientePF", "Ensino Médio completo",
         "genero", "039.729.760-20", dataNascimentoCliente);

        Seguradora seguradora = new Seguradora("11.754.368/0001-61", "Seguradora Fictícia", "(99) 99999-9999", "emailficticio@gmail.com", "endereço seguradora");
        SeguroPF seguroPF = new SeguroPF(dataInicio, dataFim, seguradora, veiculo1, clientePF);
        String filePath = "arquivos-lab6/seguros.csv";
        String headerFile = "id,dataInicio,dataFim,seguradora,listaSinistros,listaCondutores,valorMensal\n";
        seguradora.inicializarArquivo(filePath, headerFile);
        seguradora.gravarArquivo(filePath, seguroPF);
    }
}

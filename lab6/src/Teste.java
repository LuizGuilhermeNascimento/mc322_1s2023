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
         "genero", "039.729.760-20", dataNascimentoCliente, "(11)12345-6789", "email@gmail.com", "masculino");

        ClientePJ clientePJ = new ClientePJ("nome clientePF", "endereço clientePF", "32.362.791/0001-96", dataInicio,
         30, "(11)12345-6789", "email@gmail.com");

        Seguradora seguradora = new Seguradora("11.754.368/0001-61", "Seguradora Fictícia", "(99) 99999-9999", "emailficticio@gmail.com", "endereço seguradora");
        SeguroPF seguroPF = new SeguroPF(dataInicio, dataFim, seguradora, veiculo1, clientePF);

        Condutor condutor = new Condutor("039.729.760-20", "condutor", "(11)12345-6789", "endereco", "emailficticio@gmail.com", dataNascimentoCliente);
        Frota frota = new Frota();
        frota.addVeiculo(veiculo1);

        Sinistro sinistro = new Sinistro(dataFim, "endereco", condutor, seguroPF);

        // teste
        System.out.println(seguradora.lerDados("21488869839", tiposArquivos.CLIENTE_PF));


    }
}

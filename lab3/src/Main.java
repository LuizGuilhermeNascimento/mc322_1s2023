import java.util.Date;

public class Main {
    public static void main(String[] args) throws Exception {

        Seguradora seguradora = new Seguradora("Seguradora Fictícia", "(99) 99999-9999", "emailficticio@gmail.com", "endereço seguradora");

        ClientePF clientePF = new ClientePF("nome clientePF", "endereço clientePF",  new Date(),
        "escolaridade", "genero", "classe econômica", "039.729.760-20", new Date());
        ClientePJ clientePJ = new ClientePJ("Nome ficticio", "Endereco fictício", new Date(), "escolaridade", "genero",
        "classe economica", null, "45.510.467/0001-10", new Date());

        System.out.println("TESTE DAS FUNÇÕES validarCPF() e validarCNPJ(): ");
        System.out.println(clientePF.validarCPF("039.729.760-20"));
        System.out.println(clientePJ.getCNPJ());
        System.out.println("------------------------------------------------");

        seguradora.cadastrarCliente(clientePJ);
        seguradora.removerCliente(clientePF.getCpf());

        clientePF.cadastrarVeiculo(new Veiculo("ABC1D23","Marca1","Modelo1"));
        clientePJ.cadastrarVeiculo(new Veiculo("EFG1H23","Marca2","Modelo2"));

        seguradora.cadastrarCliente(clientePJ);
        seguradora.cadastrarCliente(clientePF);

        seguradora.gerarSinistro("endereço fictício", new Veiculo("ABC1D23","Marca1","Modelo1"), clientePF);

        System.out.println();

        
    }
}

import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataExemplo = sdf.parse("18/04/2023");

        // Instanciação das classes
        Seguradora seguradora = new Seguradora("Seguradora Fictícia", "(99) 99999-9999", "emailficticio@gmail.com", "endereço seguradora");
        ClientePF clientePF = new ClientePF("nome clientePF", "endereço clientePF",  dataExemplo,
        "escolaridade", "genero", "classe econômica", "039.729.760-20", dataExemplo);
        ClientePJ clientePJ = new ClientePJ("Nome ficticio", "Endereço fictício", "27.456.961/0001-61", dataExemplo);
        Veiculo veiculo1 = new Veiculo("ABC1D23","Marca1","Modelo1" , 2023);
        Veiculo veiculo2 = new Veiculo("EFG1H23","Marca2","Modelo2", 2023);

        // O CPF e CNPJ utilizados na validação foram obtidos em geradores online, portanto, são válidos
        System.out.println("\nTESTE DAS FUNÇÕES validarCPF() e validarCNPJ(): \n");
        System.out.println(clientePF.validarCPF("039.729.760-20"));
        System.out.println(clientePJ.validarCNPJ("45.510.467/0001-10"));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO E REMOÇÃO DO CLIENTE\n");
        System.out.println("Cadastramento: " + seguradora.cadastrarCliente(clientePF));
        System.out.println("Remoção: " + seguradora.removerCliente(clientePF.getCpf()));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO DOS VEÍCULOS NOS CLIENTES\n");
        System.out.println("Para o cliente PF: " + clientePF.cadastrarVeiculo(veiculo1));
        System.out.println("Para o cliente Pj: " +clientePJ.cadastrarVeiculo(veiculo2));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO DOS CLIENTES PF E PJ NA SEGURADORA\n");
        System.out.println("Cliente PF: " + seguradora.cadastrarCliente(clientePF));
        System.out.println("Cliente PJ: " + seguradora.cadastrarCliente(clientePJ));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DA GERAÇÃO DE SINISTRO\n");
        System.out.println(seguradora.gerarSinistro(dataExemplo, "endereço fictício", veiculo1, clientePF));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("LISTAGEM DOS CLIENTES\n");
        ArrayList<Cliente> arrayClientePF = seguradora.listarClientes("PF");
        for (int i = 0; i < arrayClientePF.size(); i++) { 
            System.out.println("\n----- Cliente "+(i+1)+" ----");
            System.out.println(arrayClientePF.get(i).toString());
        }
        System.out.println("\n------------------------------------------------\n");
        System.out.println("VISUALIZAÇÃO DO SINISTRO\n");
        seguradora.visualizarSinistro(clientePF.getCpf());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("LISTAGEM DOS SINISTROS");
        ArrayList<Sinistro> arraySinistro = seguradora.listarSinistros();
        for (int i = 0; i < arraySinistro.size(); i++) {
            System.out.println("\n----- Sinistro "+(i+1)+" ----");
            System.out.println(arraySinistro.get(i).toString());
        }
        System.out.println("\n------------------------------------------------\n");
        System.out.println("MÉTODO toString() DA CLASSE SEGURADORA\n");
        System.out.println(seguradora.toString());
        // OBS: Os métodos toString() das demais classes já foram utilizados na listagens dos clientes e sinistros


        // Uso da leitura de dados do teclado com System.in
        // por meio da busca de um determinado cliente a partir de seu documento
        System.out.println("\n\n------------ PESQUISE OS DADOS DE UM CLIENTE CADASTRADO A PARTIR DO DOCUMENTO ------------\n");
        Scanner reader = new Scanner(System.in);
        System.out.println("DESEJA PESQUISAR UM CLIENTE? [y/n] ");
        String resposta = reader.next();
        while(resposta.equals("y")) {

            System.out.print("Insira o documento do cliente: ");
            String doc = reader.next();
            System.out.println(doc);
            System.out.println("\n\nBuscando...\n\n");
            String clienteEncontrado = seguradora.procurarClientePorDocumento(doc);
            System.out.println(clienteEncontrado);
            System.out.println("\nDESEJA PESQUISAR UM CLIENTE? [y/n] ");
            resposta = reader.next();
        }
        reader.close();

        
    }
}

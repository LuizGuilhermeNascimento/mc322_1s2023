import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AppMain {

    /**
     * Lê os dados de um cliente PF/PJ do teclado
     * @return Objeto Cliente
     */
    public static Cliente lerDadosCliente(Scanner leitor) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Deseja cadastrar um cliente [1] PF ou [2] PJ? ");
        int tipoCliente = leitor.nextInt();
        leitor.nextLine();
        System.out.print("Nome: ");
        String nome = leitor.nextLine();
        System.out.print("Endereço: ");
        String endereco = leitor.nextLine();
        if (tipoCliente == 1) {
            System.out.print("Data de licença: ");
            String dataLicenca = leitor.next();
            leitor.nextLine();
            System.out.print("Educacao: ");
            String educacao = leitor.nextLine();
            leitor.nextLine();
            System.out.print("Gênero: ");
            String genero = leitor.nextLine();
            leitor.nextLine();
            System.out.print("Classe econômica: ");
            String classeEconomica = leitor.nextLine();
            System.out.print("CPF: ");
            String cpf = leitor.next();
            System.out.print("Data de nascimento: ");
            String dataNascimento = leitor.next();
            return new ClientePF(nome, endereco, sdf.parse(dataLicenca), educacao, genero, classeEconomica, cpf, sdf.parse(dataNascimento));
        }
        System.out.print("CNPJ: ");
        String cnpj = leitor.next();
        System.out.print("Data de fundação: ");
        String dataFundacao = leitor.next();
        System.out.print("Quantidade de funcionários: ");
        int qtdeFuncionarios = leitor.nextInt();
        return new ClientePJ(nome, endereco, cnpj, sdf.parse(dataFundacao), qtdeFuncionarios);
    }

    /**
     * Lê os dados de um veículo do teclado
     * @return Objeto Veículo
     */
    public static Veiculo lerDadosVeiculo(Scanner leitor) throws Exception{

        System.out.print("Placa: ");
        String placa = leitor.next();
        leitor.nextLine();
        System.out.print("Marca: ");
        String marca = leitor.nextLine();
        System.out.print("Modelo: ");
        String modelo = leitor.nextLine();
        System.out.print("Ano de fabricação: ");
        int anoFabricacao = leitor.nextInt();

        return new Veiculo(placa, marca, modelo, anoFabricacao);
    }

    /**
     * Parte do Menu Interativo responsável pelo Cadastro
     */
    public static void MenuCadastro(Scanner leitor, ArrayList<Seguradora> listaSeguradoras) throws Exception{
        boolean voltar = false;
        while(!voltar) {
            String nomeSeguradora;
            boolean operacaoConcluida = false;
            System.out.println("\n---------- CADASTRO ----------\n");
            System.out.println("[1] CADASTRAR CLIENTE PF/PJ");
            System.out.println("[2] CADASTRAR VEÍCULO");
            System.out.println("[3] CADASTRAR SEGURADORA");
            System.out.println("[0] VOLTAR");
            System.out.println("\n------------------------------------------------\n");
            System.out.print("Digite o número do que você deseja cadastrar: ");
            int operacao = leitor.nextInt();
            leitor.nextLine();
            System.out.println("\n------------------------------------------------\n");
            switch (operacao) {
                case 1:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        Cliente cliente = lerDadosCliente(leitor);
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarCliente(listaSeguradoras, nomeSeguradora, cliente);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 2:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.next();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        Veiculo veiculo = lerDadosVeiculo(leitor);
                        System.out.print("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarVeiculo(listaSeguradoras, nomeSeguradora, clienteDocumento, veiculo);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 3:
                    System.out.print("Nome: ");
                    nomeSeguradora = leitor.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = leitor.next();
                    System.out.print("Email: ");
                    String email = leitor.next();
                    leitor.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = leitor.nextLine();
                    operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarSeguradora(listaSeguradoras, new Seguradora(nomeSeguradora, telefone, email, endereco));
                    break;
                case 0:
                    return;
            }
            if (operacaoConcluida) {
                System.out.println("\nOperação concluída com sucesso!");
            } else {
                System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
            }
        }
    }

    /**
     * Parte do Menu Interativo responsável pela Listagem
     */
    public static void MenuListagem(Scanner leitor, ArrayList<Seguradora> listaSeguradoras) {
        boolean voltar = false;
        while(!voltar) {
            String nomeSeguradora;
            System.out.println("\n---------- LISTAGEM ----------\n");
            System.out.println("[1] LISTAR CLIENTE (PF/PJ) POR SEGURADORA");
            System.out.println("[2] LISTAR SINISTROS POR SEGURADORA");
            System.out.println("[3] LISTAR SINISTRO POR CLIENTE");
            System.out.println("[4] LISTAR VEÍCULO POR CLIENTE");
            System.out.println("[5] LISTAR VEÍCULO POR SEGURADORA");
            System.out.println("[0] VOLTAR");
            System.out.println("\n------------------------------------------------\n");
            System.out.print("Digite o número do que você deseja cadastrar: ");
            int operacao = leitor.nextInt();
            leitor.nextLine();
            System.out.println("\n------------------------------------------------\n");
            switch (operacao) {
                case 1:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.println(MenuOperacoes.LISTAR.listarClientesPorSeguradora(listaSeguradoras, nomeSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 2:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.next();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.println(MenuOperacoes.LISTAR.listarSinistrosPorSeguradora(listaSeguradoras, nomeSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 3:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.next();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.println("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        System.out.println(MenuOperacoes.LISTAR.listarVeiculoPorCliente(listaSeguradoras, clienteDocumento));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 4:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.next();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.println("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        System.out.println(MenuOperacoes.LISTAR.listarSinistroPorCliente(listaSeguradoras, nomeSeguradora, clienteDocumento));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 5:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.next();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.println(MenuOperacoes.LISTAR.listarVeiculoPorSeguradora(listaSeguradoras, nomeSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 0:
                    voltar = true;
                    break;
            }
        }
    }

    /**
     * Parte do Menu Interativo responsável pela Exclusão
     */
    public static void MenuExclusão(Scanner leitor, ArrayList<Seguradora> listaSeguradoras) {
        boolean voltar = false;
        while(!voltar) {
            String nomeSeguradora;
            boolean operacaoConcluida = false;
            System.out.println("\n---------- EXCLUSÃO ----------\n");
            System.out.println("[1] EXCLUIR CLIENTE");
            System.out.println("[2] EXCLUIR VEÍCULO");
            System.out.println("[3] EXCLUIR SINISTRO");
            System.out.println("[0] VOLTAR");
            System.out.println("\n------------------------------------------------\n");
            System.out.print("Digite o número do que você deseja cadastrar: ");
            int operacao = leitor.nextInt();
            leitor.nextLine();
            System.out.println("\n------------------------------------------------\n");
            switch (operacao) {
                case 1:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.print("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        operacaoConcluida = MenuOperacoes.EXCLUIR.excluirCliente(listaSeguradoras, nomeSeguradora, clienteDocumento);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 2:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.print("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        System.out.print("Placa do veículo: ");
                        String placa = leitor.next();
                        operacaoConcluida = MenuOperacoes.EXCLUIR.excluirVeiculo(listaSeguradoras, nomeSeguradora, clienteDocumento, placa);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 3:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.print("ID do sinistro: ");
                        int idSinistro = leitor.nextInt();
                        operacaoConcluida = MenuOperacoes.EXCLUIR.excluirSinistro(listaSeguradoras, nomeSeguradora, idSinistro);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 0:
                    return;
            }
            if (operacaoConcluida) {
                System.out.println("\nOperação concluída com sucesso!");
            } else {
                System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
            }
        }
    }

    /**
     * Função responsável pelo Menu Interativo (especialmente o menu principal)
     */
    public static void MenuOperacoesInterativo() throws Exception {
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
        Scanner leitor = new Scanner(System.in);
        boolean sair = false;
        while (!sair) {
            String nomeSeguradora;
            boolean operacaoConcluida = false;
            System.out.println("\n---------- MENU PRINCIPAL ----------\n");
            System.out.println("[1] CADASTRAR");
            System.out.println("[2] LISTAR");
            System.out.println("[3] EXCLUIR");
            System.out.println("[4] GERAR SINISTRO");
            System.out.println("[5] TRANSFERIR SEGURO");
            System.out.println("[6] CALCULAR RECEITA DA SEGURADORA");
            System.out.println("[0] SAIR");
            System.out.println("\n------------------------------------------------\n");
            System.out.print("Digite o número da operação desejada: ");
            int operacao = leitor.nextInt();
            leitor.nextLine();
            System.out.println("\n------------------------------------------------\n");
            switch (operacao) {
                case 1:
                    MenuCadastro(leitor, listaSeguradoras);
                    break;
                case 2:
                    MenuListagem(leitor, listaSeguradoras);
                    break;
                case 3:
                    MenuExclusão(leitor, listaSeguradoras);
                    break;
                case 4:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.print("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        Veiculo veiculo = lerDadosVeiculo(leitor);
                        operacaoConcluida = MenuOperacoes.GERAR_SINISTRO.gerarSinistro(listaSeguradoras, nomeSeguradora, clienteDocumento, veiculo);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    if (operacaoConcluida) {
                        System.out.println("\nOperação concluída com sucesso!");
                    } else {
                        System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
                    }
                    break;
                case 5:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.print("Documento do remetente: ");
                        String clienteFonteDocumento = leitor.next();
                        System.out.print("Documento do destinatário: ");
                        String clienteDestinoDocumento = leitor.next();
                        operacaoConcluida = MenuOperacoes.TRASNFERIR_SEGURO.transferirSeguro(listaSeguradoras, nomeSeguradora, clienteFonteDocumento, clienteDestinoDocumento);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    if (operacaoConcluida) {
                        System.out.println("\nOperação concluída com sucesso!");
                    } else {
                        System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
                    }
                    break;
                case 6:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if (MenuOperacoes.VERIFICACAO.existeSeguradora(listaSeguradoras, nomeSeguradora)) {
                        System.out.println("Receita: "+MenuOperacoes.CALCULAR_RECEITA_SEGURADORA.calcularReceitaSeguradora(listaSeguradoras, nomeSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    if (operacaoConcluida) {
                        System.out.println("\nOperação concluída com sucesso!");
                    } else {
                        System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
                    }
                    break;
                case 0:
                    return;
            }
        }
        leitor.close();
    }

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataExemplo = sdf.parse("18/04/2023");
        Date dataNascimentoCliente = sdf.parse("10/01/2005");

        // Instanciação das classes
        Veiculo veiculo1 = new Veiculo("ABC1D23","Marca1","Modelo1" , 2023);
        Veiculo veiculo2 = new Veiculo("EFG1H23","Marca2","Modelo2", 2023);
        ClientePF clientePF = new ClientePF("nome clientePF", "endereço clientePF",  dataExemplo,
        "escolaridade", "genero", "classe econômica", "039.729.760-20", dataNascimentoCliente);
        ClientePJ clientePJ = new ClientePJ("Nome ficticio", "endereço clientePJ", "27.456.961/0001-61", dataExemplo, 20);
        Seguradora seguradora = new Seguradora("Seguradora Fictícia", "(99) 99999-9999", "emailficticio@gmail.com", "endereço seguradora");
        
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
        System.out.println("1º sinistro: "+seguradora.gerarSinistro(dataExemplo, "endereço fictício", veiculo1, clientePF));
        System.out.println("2º sinistro: "+seguradora.gerarSinistro(dataExemplo, "endereço fictício", veiculo2, clientePJ));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("LISTAGEM DOS CLIENTES\n");
        ArrayList<Cliente> arrayClientePF = seguradora.listarClientes();
        for (int i = 0; i < arrayClientePF.size(); i++) { 
            System.out.println("\n----- Cliente "+(i+1)+" ----");
            System.out.println(arrayClientePF.get(i).toString());
        }
        System.out.println("\n------------------------------------------------\n");
        System.out.println("VISUALIZAÇÃO DO SINISTRO\n");
        System.out.println(seguradora.visualizarSinistro(clientePF.getCpf()));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("LISTAGEM DOS SINISTROS");
        ArrayList<Sinistro> arraySinistro = seguradora.listarSinistros();
        for (int i = 0; i < arraySinistro.size(); i++) {
            System.out.println("\n----- Sinistro "+(i+1)+" ----");
            System.out.println(arraySinistro.get(i).toString());
        }
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CÁLCULO DA RECEITA DA SEGURADORA");
        System.out.println(seguradora.calcularReceita());
        System.out.println("\n------------------------------------------------\n");
        MenuOperacoesInterativo();
    }
}

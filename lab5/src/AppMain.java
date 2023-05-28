import java.util.Date;
import java.util.HashMap;
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

    public static void MenuSinistro(Scanner leitor, BancoDeSeguradoras bancoDeSeguradoras) {
        System.out.print("Nome da Seguradora: ");
        nomeSeguradora = leitor.nextLine();
        if (bancoDeSeguradoras.contemSeguradora(nomeSeguradora)) {
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
    }

    /**
     * Função responsável pelo Menu Interativo (especialmente o menu principal)
     */
    public static void MenuOperacoesInterativo() throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        BancoDeSeguradoras bancoDeSeguradoras = new BancoDeSeguradoras();
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
            System.out.println("[5] GERAR SEGURO");
            System.out.println("[6] CALCULAR RECEITA DA SEGURADORA");
            System.out.println("[0] SAIR");
            System.out.println("\n------------------------------------------------\n");
            System.out.print("Digite o número da operação desejada: ");
            int operacao = leitor.nextInt();
            leitor.nextLine();
            System.out.println("\n------------------------------------------------\n");
            switch (operacao) {
                case 1:
                    MenuCadastro(leitor, bancoDeSeguradoras);
                    break;
                case 2:
                    MenuListagem(leitor, bancoDeSeguradoras);
                    break;
                case 3:
                    MenuExclusão(leitor, bancoDeSeguradoras);
                    break;
                case 4:
                    MenuSinistro(leitor, bancoDeSeguradoras);
                    break;
                case 5:
                    System.out.print("Nome da Seguradora: ");
                    nomeSeguradora = leitor.nextLine();
                    if(bancoDeSeguradoras.contemSeguradora(nomeSeguradora)) {
                        System.out.print("Data de início: ");
                        Date dataInicio = sdf.parse(leitor.next());
                        System.out.print("Data de fim: ");
                        Date dataFim = sdf.parse(leitor.next());
                        System.out.print("Documento do cliente: ");
                        String documentoCliente = leitor.next();
                        operacaoConcluida = MenuOperacoes.GERAR_SEGURO.gerarSeguro(bancoDeSeguradoras, nomeSeguradora, dataInicio, dataFim, documentoCliente);
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
                    if(bancoDeSeguradoras.contemSeguradora(nomeSeguradora)) {
                        System.out.println("Receita: "+MenuOperacoes.CALCULAR_RECEITA_SEGURADORA.calcularReceitaSeguradora(nomeSeguradora));
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
        Date dataNascimentoCondutor1 = sdf.parse("12/02/1995");
        Date dataNascimentoCondutor2 = sdf.parse("19/08/1999");
        Date dataInicio = sdf.parse("03/12/2020");
        Date dataFim = sdf.parse("01/01/2024");
        Date dataSinistro = sdf.parse("06/05/2021");

        // Instanciação das classes
        Veiculo veiculo1 = new Veiculo("ABC1D23","Marca1","Modelo1" , 2023);
        Veiculo veiculo2 = new Veiculo("EFG1H23","Marca2","Modelo2", 2023);
        ClientePF clientePF = new ClientePF("nome clientePF", "endereço clientePF", "Ensino Médio completo",
         "genero", "039.729.760-20", dataNascimentoCliente);
        ClientePJ clientePJ = new ClientePJ("Nome ficticio", "endereço clientePJ", "27.456.961/0001-61", dataExemplo, 20);
        Seguradora seguradora = new Seguradora("Seguradora Fictícia", "(99) 99999-9999", "emailficticio@gmail.com", "endereço seguradora");
        Frota frota = new Frota();
        Condutor condutor1 = new Condutor("940.153.620-12", "Condutor1", "(99)99999-9999", "Endereço condutor 1", "condutor1@gmail.com", dataNascimentoCondutor1);
        Condutor condutor2 = new Condutor("540.626.080-49", "Condutor2", "(99)99999-9999", "Endereço condutor 2", "condutor2@gmail.com", dataNascimentoCondutor2);

        
        SeguroPF seguroPF = new SeguroPF(dataInicio, dataFim, seguradora, veiculo1, clientePF);
        SeguroPJ seguroPJ = new SeguroPJ(dataInicio, dataFim, seguradora, frota, clientePJ);


        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO DO VEÍCULO NO CLIENTE PF\n");
        System.out.println(clientePF.cadastrarVeiculo(veiculo1));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO DA FROTA NO CLIENTE PJ\n");
        System.out.println(clientePJ.cadastrarFrota(frota));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO DO SINISTRO DO CONDUTOR\n");
        System.out.println(condutor1.adicionarSinistro(dataSinistro, "Endereço sinistro", veiculo2, seguroPJ));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO E REMOÇÃO DE VEÍCULO NA FROTA\n");
        System.out.println("Cadastro: "+frota.addVeiculo(veiculo1));
        System.out.println("Remoção: "+frota.removeVeiculo(veiculo1));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DA GERAÇÃO DE SINISTRO NO SEGURO\n");
        System.out.println(seguroPF.gerarSinistro(dataSinistro, "Endereço sinistro", "540.626.080-49"));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CÁLCULO DA FUNÇÃO calculaValor()\n");
        System.out.println(seguroPF.calculaValor());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DA GERAÇÃO DE SEGURO NA SEGURADORA\n");
        System.out.println("Para um cliente PF: "+seguradora.gerarSeguro(dataInicio, dataFim, veiculo2, clientePF));
        System.out.println("Para um cliente PJ: "+seguradora.gerarSeguro(dataInicio, dataFim, frota, clientePJ));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DA GERAÇÃO DE SINISTRO NA SEGURADORA\n");
        System.out.println(seguradora.getListaSeguros().get(0).gerarSinistro(dataSinistro, "Endereço sinistro", "940.153.620-12"));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO DOS CLIENTES PF E PJ NA SEGURADORA\n");
        System.out.println("Cliente PF: " + seguradora.cadastrarCliente(clientePF));
        System.out.println("Cliente PJ: " + seguradora.cadastrarCliente(clientePJ));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CANCELAMENTO DE SINISTRO NA SEGURADORA\n");
        System.out.println(seguradora.cancelarSeguro("039.729.760-20", "EFG1H23"));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CÁLCULO DA RECEITA DA SEGURADORA");
        System.out.println(seguradora.calcularReceita());
        System.out.println("\n------------------------------------------------\n");


        System.out.println("\n------------------------------------------------\n");
        System.out.println("MÉTODOS toString() DAS CLASSES INSTANCIADAS");
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE VEÍCULO: ");
        System.out.println(veiculo1.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE CLIENTE PF: ");
        System.out.println(clientePF.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE CLIENTE PJ: ");
        System.out.println(clientePJ.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE FROTA: ");
        System.out.println(frota.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE CONDUTOR: ");
        System.out.println(condutor1.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE SEGURADORA: ");
        System.out.println(seguradora.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE SEGURO: ");
        System.out.println(seguroPF.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE SINISTRO: ");
        System.out.println(seguradora.getListaSeguros().get(0).getListaSinistros().get(0).toString());

        MenuOperacoesInterativo();
    }
}

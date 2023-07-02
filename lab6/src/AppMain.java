import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AppMain {

    /**
     * Lê os dados de um cliente PF/PJ do teclado
     * @return Objeto Cliente
     */
    public static Cliente lerDadosCliente(Scanner leitor) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String email = "";
        String telefone = "";

        System.out.print("Deseja cadastrar um cliente PF ou PJ? [PF/PJ]");
        String tipoCliente = leitor.next();
        leitor.nextLine();
        System.out.print("Nome: ");
        String nome = leitor.nextLine();
        System.out.print("Endereço: ");
        String endereco = leitor.nextLine();
        switch (tipoCliente) {
            case "PF":
                System.out.print("Educacao: ");
                String educacao = leitor.nextLine();
                System.out.print("Gênero: ");
                String genero = leitor.nextLine();
                System.out.print("CPF: ");
                String cpf = leitor.next();
                System.out.print("Data de nascimento: ");
                String dataNascimento = leitor.next();
                System.out.print("Telefone: ");
                telefone = leitor.next();
                System.out.print("Email: ");
                email = leitor.next();
                return new ClientePF(nome, endereco, educacao, genero, cpf, sdf.parse(dataNascimento), telefone, email);
            case "PJ":
                System.out.print("CNPJ: ");
                String cnpj = leitor.next();
                System.out.print("Data de fundação: ");
                String dataFundacao = leitor.next();
                System.out.print("Quantidade de funcionários: ");
                int qtdeFuncionarios = leitor.nextInt();
                System.out.print("Telefone: ");
                telefone = leitor.next();
                System.out.print("Email: ");
                email = leitor.next();
                return new ClientePJ(nome, endereco, cnpj, sdf.parse(dataFundacao), qtdeFuncionarios, telefone, email);
        }
        return null;
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

    public static Condutor lerDadosCondutor(Scanner leitor) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.print("CPF: ");
        String cpf = leitor.next();
        System.out.print("Nome: ");
        String nome = leitor.nextLine();
        System.out.print("Telefone: ");
        String telefone = leitor.next();
        System.out.print("Endereço: ");
        String endereco = leitor.nextLine();
        System.out.print("Email: ");
        String email = leitor.next();
        System.out.print("Data de nascimento: ");
        Date dataNascimento = sdf.parse(leitor.next());

        return new Condutor(cpf, nome, telefone, endereco, email, dataNascimento);
    }

    /**
     * Parte do Menu Interativo responsável pelo Cadastro
     */
    public static void MenuCadastro(Scanner leitor, BancoDeSeguradoras bancoDeSeguradoras) throws Exception{
        boolean voltar = false;
        while(!voltar) {
            String cnpjSeguradora;
            boolean operacaoConcluida = false;
            System.out.println("\n---------- CADASTRO ----------\n");
            System.out.println("[1] CADASTRAR CLIENTE PF/PJ");
            System.out.println("[2] CADASTRAR VEÍCULO NO CLIENTE");
            System.out.println("[3] CADASTRAR VEÍCULO NA FROTA");
            System.out.println("[4] CADASTRAR SEGURADORA");
            System.out.println("[5] CADASTRAR FROTA");
            System.out.println("[6] CADASTRAR CONDUTOR");
            System.out.println("[0] SAIR");
            System.out.println("\n------------------------------------------------\n");
            System.out.print("Digite o número do que você deseja cadastrar: ");
            int operacao = leitor.nextInt();
            leitor.nextLine();
            System.out.println("\n------------------------------------------------\n");
            switch (operacao) {
                case 1:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.nextLine();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        Cliente cliente = lerDadosCliente(leitor);
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarCliente(bancoDeSeguradoras, cnpjSeguradora, cliente);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 2:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        Veiculo veiculo = lerDadosVeiculo(leitor);
                        System.out.print("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarVeiculoNoCliente(bancoDeSeguradoras, cnpjSeguradora, clienteDocumento, veiculo);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 3:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        Veiculo veiculo = lerDadosVeiculo(leitor);
                        System.out.print("Código da frota: ");
                        String codeFrota = leitor.next();
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarVeiculoNaFrota(bancoDeSeguradoras, cnpjSeguradora, codeFrota, veiculo);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 4:
                    System.out.print("CNPJ: ");
                    String cnpj = leitor.next();
                    leitor.nextLine();
                    System.out.print("Nome: ");
                    String nomeSeguradora = leitor.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = leitor.next();
                    System.out.print("Email: ");
                    String email = leitor.next();
                    leitor.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = leitor.nextLine();
                    operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarSeguradora(bancoDeSeguradoras, new Seguradora(cnpj, nomeSeguradora, telefone, email, endereco));
                    break;
                case 5:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.print("CNPJ do cliente: ");
                        String documentoCliente = leitor.next();
                        Frota novaFrota = new Frota();
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarFrota(bancoDeSeguradoras, cnpjSeguradora, novaFrota, documentoCliente);
                        System.out.println("Código da frota cadastrada: "+novaFrota.getCode());
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 6:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        Condutor condutor = lerDadosCondutor(leitor);
                        System.out.print("ID do seguro: ");
                        int idSeguro = leitor.nextInt();
                        operacaoConcluida = MenuOperacoes.CADASTRAR.cadastrarCondutor(bancoDeSeguradoras, cnpjSeguradora, idSeguro, condutor);
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
     * Parte do Menu Interativo responsável pela Listagem
     */
    public static void MenuListagem(Scanner leitor, BancoDeSeguradoras bancoDeSeguradoras) {
        boolean voltar = false;
        while(!voltar) {
            String cnpjSeguradora;
            System.out.println("\n---------- LISTAGEM ----------\n");
            System.out.println("[1] LISTAR CLIENTE (PF/PJ) POR SEGURADORA");
            System.out.println("[2] LISTAR SINISTROS POR SEGURADORA");
            System.out.println("[3] LISTAR SINISTROS POR CLIENTE");
            System.out.println("[4] LISTAR VEÍCULOS POR CLIENTE");
            System.out.println("[5] LISTAR VEÍCULOS POR SEGURADORA");
            System.out.println("[6] LISTAR CONDUTORES POR SEGURO");
            System.out.println("[7] LISTAR FROTAS POR SEGURADORA");
            System.out.println("[8] LISTAR FROTAS POR CLIENTE");
            System.out.println("[9] LISTAR SEGUROS POR SEGURADORA");
            System.out.println("[0] VOLTAR");
            System.out.println("\n------------------------------------------------\n");
            System.out.print("Digite o número do que você deseja cadastrar: ");
            int operacao = leitor.nextInt();
            leitor.nextLine();
            System.out.println("\n------------------------------------------------\n");
            switch (operacao) {
                case 1:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.nextLine();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println(MenuOperacoes.LISTAR.listarClientesPorSeguradora(bancoDeSeguradoras, cnpjSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 2:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println(MenuOperacoes.LISTAR.listarSinistrosPorSeguradora(bancoDeSeguradoras, cnpjSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 3:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        System.out.println(MenuOperacoes.LISTAR.listarSinistroPorCliente(bancoDeSeguradoras, cnpjSeguradora, clienteDocumento));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                    
                case 4:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        System.out.println(MenuOperacoes.LISTAR.listarVeiculoPorCliente(bancoDeSeguradoras, cnpjSeguradora, clienteDocumento));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 5:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println(MenuOperacoes.LISTAR.listarVeiculoPorSeguradora(bancoDeSeguradoras, cnpjSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 6:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.print("ID do seguro: ");
                        int idSeguro = leitor.nextInt();
                        System.out.println(MenuOperacoes.LISTAR.listarCondutoresPorSeguro(bancoDeSeguradoras, cnpjSeguradora, idSeguro));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 7:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println(MenuOperacoes.LISTAR.listarFrotasPorSeguradora(bancoDeSeguradoras, cnpjSeguradora));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 8:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        System.out.println(MenuOperacoes.LISTAR.listarFrotasPorCliente(bancoDeSeguradoras, cnpjSeguradora, clienteDocumento));
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 9:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.next();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println(MenuOperacoes.LISTAR.listarSegurosPorSeguradora(bancoDeSeguradoras, cnpjSeguradora));
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
    public static void MenuExclusão(Scanner leitor, BancoDeSeguradoras bancoDeSeguradoras) {
        boolean voltar = false;
        while(!voltar) {
            String cnpjSeguradora;
            boolean operacaoConcluida = false;
            System.out.println("\n---------- EXCLUSÃO ----------\n");
            System.out.println("[1] EXCLUIR CLIENTE");
            System.out.println("[2] EXCLUIR VEÍCULO");
            System.out.println("[3] EXCLUIR FROTA");
            System.out.println("[4] EXCLUIR CONDUTOR");
            System.out.println("[5] EXCLUIR SINISTRO");
            System.out.println("[0] VOLTAR");
            System.out.println("\n------------------------------------------------\n");
            System.out.print("Digite o número do que você deseja cadastrar: ");
            int operacao = leitor.nextInt();
            leitor.nextLine();
            System.out.println("\n------------------------------------------------\n");
            switch (operacao) {
                case 1:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.nextLine();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.print("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        operacaoConcluida = MenuOperacoes.EXCLUIR.excluirCliente(bancoDeSeguradoras, cnpjSeguradora, clienteDocumento);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 2:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.nextLine();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println("Deseja excluir um veículo de um cliente PF ou PJ? [PF/PJ]");
                        String tipoCliente = leitor.next();
                        System.out.print("Documento do cliente: ");
                        String clienteDocumento = leitor.next();
                        System.out.print("Placa do veículo: ");
                        String placa = leitor.next();
                        switch (tipoCliente) {
                            case "PF":
                                operacaoConcluida = MenuOperacoes.EXCLUIR.excluirVeiculoPorClientePF(bancoDeSeguradoras, cnpjSeguradora, clienteDocumento, placa);
                            case "PJ":
                                System.out.print("Código da frota: ");
                                String codeFrota = leitor.next();
                                operacaoConcluida = MenuOperacoes.EXCLUIR.excluirVeiculoPorClientePJ(bancoDeSeguradoras, cnpjSeguradora, codeFrota, clienteDocumento, placa);
                        }
    
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 3:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.nextLine();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.print("Código da frota: ");
                        String codeFrota = leitor.next();
                        operacaoConcluida = MenuOperacoes.EXCLUIR.excluirFrota(bancoDeSeguradoras, cnpjSeguradora, codeFrota);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 4:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.nextLine();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.print("Documento do condutor: ");
                        String documentoCondutor = leitor.next();
                        operacaoConcluida = MenuOperacoes.EXCLUIR.excluirCondutor(bancoDeSeguradoras, cnpjSeguradora, documentoCondutor);
                    } else {
                        System.out.println("A seguradora inserida não está cadastrada!");
                    }
                    break;
                case 5:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.nextLine();
                    if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println("Deseja excluir sinistro de um condutor ou de um seguro? [C/S]");
                        String tipoSinistro = leitor.next();
                        System.out.print("ID do sinistro: ");
                        int idSinistro = leitor.nextInt();
                        switch (tipoSinistro) {
                            case "C":
                                System.out.print("Documento do condutor: ");
                                String documentoCondutor = leitor.next();
                                operacaoConcluida = MenuOperacoes.EXCLUIR.excluirSinistroPorCondutor(bancoDeSeguradoras, cnpjSeguradora, documentoCondutor, idSinistro);
                            case "S":
                                System.out.print("ID do seguro: ");
                                int idSeguro = leitor.nextInt();
                                operacaoConcluida = MenuOperacoes.EXCLUIR.excluirSinistroPorSeguro(bancoDeSeguradoras, cnpjSeguradora, idSinistro, idSeguro);
                        }
    
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

    public static void MenuSinistro(Scanner leitor, BancoDeSeguradoras bancoDeSeguradoras) throws Exception{
        System.out.print("CNPJ da Seguradora: ");
        String cnpjSeguradora = leitor.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean operacaoConcluida = false;

        if (bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
            System.out.print("Data: ");
            Date data = sdf.parse(leitor.next());
            System.out.print("Endereço: ");
            String endereco = leitor.nextLine();
            System.out.print("Documento do condutor: ");
            String documentoCondutor = leitor.next();
            System.out.print("ID do seguro: ");
            int idSeguro = leitor.nextInt();
            System.out.print("Placa do veículo: ");
            String placa = leitor.next();

            operacaoConcluida = MenuOperacoes.GERAR_SINISTRO.gerarSinistro(bancoDeSeguradoras, cnpjSeguradora, data, endereco, documentoCondutor, idSeguro, placa);
        } else {
            System.out.println("A seguradora inserida não está cadastrada!");
        }
        if (operacaoConcluida) {
            System.out.println("\nOperação concluída com sucesso!");
        } else {
            System.out.println("\nErro! A operação não foi possível de ser concluída! Tente novamente!");
        }
    }

    public static void MenuSeguro(Scanner leitor, BancoDeSeguradoras bancoDeSeguradoras) throws Exception{

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        boolean operacaoConcluida = false;
        System.out.print("CNPJ da Seguradora: ");
        String cnpjSeguradora = leitor.nextLine();

        if(bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
            System.out.print("Data de início: ");
            Date dataInicio = sdf.parse(leitor.next());
            System.out.print("Data de fim: ");
            Date dataFim = sdf.parse(leitor.next());
            System.out.print("Deseja criar um seguro para um cliente PF ou PJ ? [PF/PJ]");
            String tipoCliente = leitor.next();
            Cliente cliente;
            switch(tipoCliente) {
                case "PF":
                Veiculo veiculo = lerDadosVeiculo(leitor);
                cliente = lerDadosCliente(leitor);
                operacaoConcluida = MenuOperacoes.GERAR_SEGURO.gerarSeguroPF(bancoDeSeguradoras, cnpjSeguradora, dataInicio, dataFim, veiculo, (ClientePF)cliente);
                case "PJ":
                cliente = lerDadosCliente(leitor);
                operacaoConcluida = MenuOperacoes.GERAR_SEGURO.gerarSeguroPJ(bancoDeSeguradoras, cnpjSeguradora, dataInicio, dataFim,(ClientePJ)cliente);
            }
            
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

        BancoDeSeguradoras bancoDeSeguradoras = new BancoDeSeguradoras();
        Scanner leitor = new Scanner(System.in);
        boolean sair = false;
        while (!sair) {
            String cnpjSeguradora;
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
                    MenuSeguro(leitor, bancoDeSeguradoras);
                    break;
                case 6:
                    System.out.print("CNPJ da Seguradora: ");
                    cnpjSeguradora = leitor.nextLine();
                    if(bancoDeSeguradoras.contemSeguradora(cnpjSeguradora)) {
                        System.out.println("Receita: "+MenuOperacoes.CALCULAR_RECEITA_SEGURADORA.calcularReceitaSeguradora(bancoDeSeguradoras, cnpjSeguradora));
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
         "genero", "039.729.760-20", dataNascimentoCliente, "(11) 12345-6789", "email@gmail.com");
        ClientePJ clientePJ = new ClientePJ("Nome ficticio", "endereço clientePJ", "27.456.961/0001-61", dataExemplo, 20, "(11) 12345-6789", "email@gmail.com");
        Seguradora seguradora = new Seguradora("11.754.368/0001-61", "Seguradora Fictícia", "(99) 99999-9999", "emailficticio@gmail.com", "endereço seguradora");
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
        System.out.println("Remoção: "+frota.removeVeiculo(veiculo1.getPlaca()));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DA GERAÇÃO DE SINISTRO NO SEGURO\n");
        // adiciona um condutor ao seguro
        seguroPF.autorizarCondutor(condutor2);
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
        // adiciona um condutor ao seguro
        seguradora.getListaSeguros().get(0).autorizarCondutor(condutor2);
        // gera um sinistro relacionado ao condutor previamente adicionado
        System.out.println(seguradora.getListaSeguros().get(0).gerarSinistro(dataSinistro, "Endereço sinistro", "540.626.080-49"));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CADASTRO DOS CLIENTES PF E PJ NA SEGURADORA\n");
        System.out.println("Cliente PF: " + seguradora.cadastrarCliente(clientePF));
        System.out.println("Cliente PJ: " + seguradora.cadastrarCliente(clientePJ));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("FEEDBACK DO CANCELAMENTO DE SINISTRO NA SEGURADORA\n");
        // utiliza o id para cancelar um sinistro
        int idSinistro = seguradora.getListaSeguros().get(0).getListaSinistros().get(0).getId();
        System.out.println(seguradora.getListaSeguros().get(0).removerSinistroPorId(idSinistro));
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
        System.out.println(condutor2.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE SEGURADORA: ");
        System.out.println(seguradora.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE SEGURO: ");
        System.out.println(seguroPF.toString());
        System.out.println("\n------------------------------------------------\n");
        System.out.println("CLASSE SINISTRO: ");
        // gera um sinistro para que seja possível chamar o método toString()
        seguradora.getListaSeguros().get(0).gerarSinistro(dataSinistro, "Endereço sinistro", "540.626.080-49");
        System.out.println(seguradora.getListaSeguros().get(0).getListaSinistros().get(0).toString());

        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nOPERAÇÕES EM ARQUIVOS\n");
        System.out.println("\n------------------------------------------------\n");

        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nREGISTRO DE UM CLIENTE PF: ");
        System.out.println(seguradora.gravarDados(clientePF, tiposArquivos.CLIENTE_PF));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nREGISTRO DE UM CLIENTE PJ: ");
        System.out.println(seguradora.gravarDados(clientePJ, tiposArquivos.CLIENTE_PJ));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nREGISTRO DE UM CONDUTOR: ");
        System.out.println(seguradora.gravarDados(condutor1, tiposArquivos.CONDUTOR));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nREGISTRO DE UMA FROTA: ");
        System.out.println(seguradora.gravarDados(frota, tiposArquivos.FROTA));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nREGISTRO DE UM VEICULO: ");
        System.out.println(seguradora.gravarDados(veiculo1, tiposArquivos.VEICULO));

        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nLEITURA DE UM CLIENTE PF: ");
        System.out.println(seguradora.lerDados("039.729.760-20", tiposArquivos.CLIENTE_PF));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nLEITURA DE UM CLIENTE PJ: ");
        System.out.println(seguradora.lerDados("27.456.961/0001-61", tiposArquivos.CLIENTE_PJ));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nLEITURA DE UM CONDUTOR: ");
        System.out.println(seguradora.lerDados("940.153.620-12", tiposArquivos.CONDUTOR));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nLEITURA DE UMA FROTA: ");
        System.out.println(seguradora.lerDados("006", tiposArquivos.FROTA));
        System.out.println("\n------------------------------------------------\n");
        System.out.println("\nLEITURA DE UM VEICULO: ");
        System.out.println(seguradora.lerDados("ABC1D23", tiposArquivos.VEICULO));

        MenuOperacoesInterativo();
    }
}

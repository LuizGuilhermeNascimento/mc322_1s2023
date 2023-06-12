import java.util.ArrayList;
import java.util.Date;

public enum MenuOperacoes {
    CADASTRAR (1),
    LISTAR(2),
    EXCLUIR (3),
    GERAR_SINISTRO(4),
    GERAR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    VERIFICACAO(7),
    SAIR (0);

    public final int operacao;
    private Seguradora seguradora;
    private boolean bemSucedido;

    MenuOperacoes ( int operacao ) {
        this.operacao = operacao;
    }

    public Seguradora atualizarClienteNosSeguros(Seguradora seguradora, Cliente novoCliente) {
        for (int j = 0; j < seguradora.getListaSeguros().size(); j++) {
            if (seguradora.getListaSeguros().get(j).getCliente().getDocumento().equals(novoCliente.getDocumento())) {
                seguradora.getListaSeguros().get(j).setCliente(novoCliente);
            }
        }
        return seguradora;
    }

    /////////////// OPERAÇÕES DE CADASTRO ///////////////

    public boolean cadastrarCliente(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, Cliente cliente) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        bemSucedido = seguradora.cadastrarCliente(cliente);
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return bemSucedido;
    }

    public boolean cadastrarVeiculoNoCliente(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String clienteDocumento, Veiculo veiculo) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        
        for (int i = 0; i < seguradora.listarClientes().size(); i++) {
            Cliente atualCliente = seguradora.listarClientes().get(i);
            if (atualCliente.getDocumento().equals(clienteDocumento)) {
                ClientePF novoCliente = (ClientePF)atualCliente;
                novoCliente.cadastrarVeiculo(veiculo);
                seguradora.removerCliente(clienteDocumento);
                seguradora.cadastrarCliente(novoCliente);
                seguradora = atualizarClienteNosSeguros(seguradora, novoCliente);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        return false;
    }

    public boolean cadastrarVeiculoNaFrota(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String codeFrota, Veiculo veiculo) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        
        for (int i = 0; i < seguradora.listarClientes().size(); i++) {
            Cliente atualCliente = seguradora.listarClientes().get(i);
            if (atualCliente instanceof ClientePJ) {
                ClientePJ novoCliente = (ClientePJ)atualCliente;
                novoCliente.atualizarFrota(codeFrota, FrotaOperacoes.ADICIONAR_VEICULO, veiculo);
                seguradora.removerCliente(novoCliente.getDocumento());
                seguradora.cadastrarCliente(novoCliente);
                seguradora = atualizarClienteNosSeguros(seguradora, novoCliente);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean cadastrarSeguradora(BancoDeSeguradoras bancoDeSeguradoras, Seguradora seguradora) {
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return true;
    }

    public boolean cadastrarFrota(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, Frota frota, String documentoCliente) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        for (Cliente c : seguradora.listarClientes()) {
            if (c instanceof ClientePJ) {
                ClientePJ clientePJ = (ClientePJ)c;
                clientePJ.cadastrarFrota(frota);
                seguradora.removerCliente(clientePJ.getDocumento());
                seguradora.cadastrarCliente(clientePJ);
                seguradora = atualizarClienteNosSeguros(seguradora, clientePJ);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean cadastrarCondutor(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, int idSeguro, Condutor condutor) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        for (Seguro s : seguradora.getListaSeguros()) {
            if (s.getId() == idSeguro) {
                s.autorizarCondutor(condutor);
                seguradora.cancelarSeguroPorId(idSeguro);
                seguradora.gerarSeguro(s);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    /////////////// OPERAÇÕES DE LISTAGEM ///////////////

    public String listarClientesPorSeguradora(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        if (seguradora.listarClientes().size() == 0) {
            return "A seguradora não possui clientes cadastrados!";
        }
        StringBuilder sg = new StringBuilder();
        sg.append("Clientes da Seguradora "+seguradora.getNome()+"\n");
        sg.append(seguradora.toStringListaClientes());
        return sg.toString();
    }

    public String listarSinistrosPorSeguradora(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        ArrayList<Sinistro> sinistros = seguradora.getSinistrosSeguradora();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sinistros.size(); i++) {
            sb.append("\n----- Sinistro "+(i+1)+" ----\n");
            sb.append(sinistros.get(i).toString()+"\n");
        }
        return sb.toString();
    }
    public String listarSinistroPorCliente(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String clienteDocumento) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);

        for (Seguro s : seguradora.getListaSeguros()) {
            if (s.getCliente().getDocumento().equals(clienteDocumento)) {
                if (s.getListaSinistros().size() == 0) {
                    return "O cliente não possui sinistros!";
                }
                return s.toStringListaSinistro();
            }
        }
        return "Cliente não encontrado!";
    }
    public String listarVeiculoPorCliente(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String clienteDocumento) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        StringBuilder sb = new StringBuilder();

        for (Cliente c : seguradora.listarClientes()) {
            if (c.getDocumento().equals(clienteDocumento)) {
                sb.append("\nLista de veículos:\n");
                for (int i = 0; i < c.listaVeiculosCadastrados().size(); i++) {
                    sb.append("\n----- Veículo "+(i+1)+" ----\n");
                    sb.append(c.listaVeiculosCadastrados().get(i).toString() + "\n");
                }
                return sb.toString();
            }
        }

        return "Cliente não encontrado!";
    }

    public String listarVeiculoPorSeguradora(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de veículos:\n");
        for (int i = 0; i < seguradora.getVeiculosSeguradora().size(); i++) {
            sb.append("\n----- Veículo "+(i+1)+" ----\n");
            sb.append(seguradora.getVeiculosSeguradora().get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public String listarCondutoresPorSeguro(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, int id) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        StringBuilder sb = new StringBuilder();
        for (Seguro s : seguradora.getListaSeguros()) {
            if (s.getId() == id) {
                SeguroPJ seguroPJ = (SeguroPJ)s;
                for (int i = 0; i < seguroPJ.listaCondutores.size(); i++) {
                    sb.append("\n----- Condutor "+(i+1)+" ----\n");
                    sb.append(seguroPJ.listaCondutores.get(i).toString() + "\n");
                }
            }
        }
        return sb.toString();
    }

    public String listarFrotasPorSeguradora(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Frotas: "+"\n");
        for (int i = 0; i < seguradora.getFrotaSeguradora().size(); i++) {
            sb.append("\n----- Frota "+(i+1)+" ----\n");
            sb.append(seguradora.getFrotaSeguradora().get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public String listarFrotasPorCliente(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String documentoCliente) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        StringBuilder sb = new StringBuilder();
        for (Cliente c : seguradora.listarClientes()) {
            if (c.getDocumento().equals(documentoCliente)) {
                ClientePJ clientePJ = (ClientePJ)c;
                sb.append("Lista de Frotas: "+"\n");
                for (int i = 0; i < clientePJ.listarFrotas().size(); i++) {
                    sb.append("\n----- Frota "+(i+1)+" ----\n");
                    sb.append(clientePJ.listarFrotas().get(i).toString() + "\n");
                }
            }
        }
        return sb.toString();
    }

    public String listarSegurosPorSeguradora(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Seguros: "+"\n");
        for (int i = 0; i < seguradora.getListaSeguros().size(); i++) {
            sb.append("\n----- Frota "+(i+1)+" ----\n");
            sb.append(seguradora.getListaSeguros().get(i).toString() + "\n");
        }
        return sb.toString();
    }

    /////////////// OPERAÇÕES DE EXCLUSÃO ///////////////

    public boolean excluirCliente(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String clienteDocumento) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        seguradora.removerCliente(clienteDocumento);
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return true;
    }

    public boolean excluirVeiculoPorClientePF(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String clienteDocumento, String placaVeiculo) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        for (Cliente c : seguradora.listarClientes()) {
            if (c.getDocumento().equals(clienteDocumento)) {
                ClientePF clientePF = (ClientePF)c;
                clientePF.removerVeiculo(placaVeiculo);
                seguradora.removerCliente(clienteDocumento);
                seguradora.cadastrarCliente(c);
                seguradora = atualizarClienteNosSeguros(seguradora, c);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean excluirVeiculoPorClientePJ(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String codeFrota, String clienteDocumento, String placaVeiculo) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        for (Cliente c : seguradora.listarClientes()) {
            if (c.getDocumento().equals(clienteDocumento)) {
                ClientePJ clientePJ = (ClientePJ)c;
                clientePJ.atualizarFrota(codeFrota, FrotaOperacoes.REMOVER_VEICULO, placaVeiculo);
                seguradora.removerCliente(clienteDocumento);
                seguradora.cadastrarCliente(c);
                seguradora = atualizarClienteNosSeguros(seguradora, c);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean excluirFrota(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String codeFrota) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        for(Cliente c : seguradora.listarClientes()) {
            if (c instanceof ClientePJ) {
                ClientePJ clientePJ = (ClientePJ)c;
                clientePJ.atualizarFrota(codeFrota, FrotaOperacoes.REMOVER_FROTA);
                seguradora.removerCliente(clientePJ.getDocumento());
                seguradora.cadastrarCliente(c);
                seguradora = atualizarClienteNosSeguros(seguradora, c);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean excluirCondutor(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String documentoCondutor) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        for (Seguro s : seguradora.getListaSeguros()) {
            for (Condutor c : s.getListaCondutores()) {
                if (c.getCPF().equals(documentoCondutor)) {
                    s.desautorizarCondutor(documentoCondutor);
                    seguradora.cancelarSeguroPorId(s.getId());
                    seguradora.gerarSeguro(s);
                    bancoDeSeguradoras.updateSeguradora(seguradora);
                    return true;
                }
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean excluirSinistroPorCondutor(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, String documentoCondutor, int idSinistro) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        for (Seguro s : seguradora.getListaSeguros()) {
            for (Condutor c : s.getListaCondutores()) {
                if (c.getCPF().equals(documentoCondutor)) {
                    c.removerSinistro(idSinistro);
                    s.desautorizarCondutor(documentoCondutor);
                    s.autorizarCondutor(c);
                    seguradora.cancelarSeguroPorId(s.getId());
                    seguradora.gerarSeguro(s);
                    bancoDeSeguradoras.updateSeguradora(seguradora);
                    return true;
                }
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean excluirSinistroPorSeguro(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, int idSinistro, int idSeguro) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        for (Seguro s : seguradora.getListaSeguros()) {
            if (s.getId() == idSeguro) {
                s.removerSinistroPorId(idSinistro);
                seguradora.cancelarSeguroPorId(s.getId());
                seguradora.gerarSeguro(s);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean gerarSinistro(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, Date data, String endereco, String documentoCondutor, int idSeguro, String placaVeiculo) {

        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        Veiculo veiculoToAdd = null;
        // Encontra o veículo para adicionar
        for (Veiculo v : seguradora.getVeiculosSeguradora()) {
            if (v.getPlaca().equals(placaVeiculo)) { veiculoToAdd = v; }
        }
        // Atualiza a lista de sinistros do condutor
        for (Seguro s : seguradora.getListaSeguros()) {
            for (Condutor c : s.getListaCondutores()) {
                if (c.getCPF().equals(documentoCondutor)) {
                    c.adicionarSinistro(data, endereco, veiculoToAdd, s);
                    s.desautorizarCondutor(documentoCondutor);
                    s.autorizarCondutor(c);
                    seguradora.cancelarSeguroPorId(s.getId());
                    seguradora.gerarSeguro(s);
                    bancoDeSeguradoras.updateSeguradora(seguradora);
                }
            }
        }
        // Atualiza a lista de sinistros do seguro
        for (Seguro s : seguradora.getListaSeguros()) {
            if (s.getId() == idSeguro) {
                s.gerarSinistro(data, endereco, documentoCondutor);
                seguradora.cancelarSeguroPorId(s.getId());
                seguradora.gerarSeguro(s);
                bancoDeSeguradoras.updateSeguradora(seguradora);
                return true;
            }
        }
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return false;
    }

    public boolean gerarSeguroPF(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, Date dataInicio, Date dataFim, Veiculo veiculo, ClientePF cliente) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        seguradora.gerarSeguro(dataInicio, dataFim, veiculo, cliente);
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return true;
    }

    public boolean gerarSeguroPJ(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora, Date dataInicio, Date dataFim, ClientePJ cliente) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        seguradora.gerarSeguro(dataInicio, dataFim, new Frota(), cliente);
        bancoDeSeguradoras.updateSeguradora(seguradora);
        return true;
    }

    public double calcularReceitaSeguradora(BancoDeSeguradoras bancoDeSeguradoras, String cnpjSeguradora) {
        seguradora = bancoDeSeguradoras.getSeguradora(cnpjSeguradora);
        return seguradora.calcularReceita();
    }

    public int getOperacao () {
        return this.operacao ;
    }
}

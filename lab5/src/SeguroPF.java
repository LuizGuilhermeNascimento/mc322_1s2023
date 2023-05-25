public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF clientePF;
    
    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora, cliente);
        this.veiculo = veiculo;
        this.clientePF = cliente;
    }

    public Frota getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    @Override
    public double calculaValor() {
        int idadeCliente = clientePF.calcularIdade(clientePF.getDataNascimento());
        int qtdeSinistrosCondutor = 0;
        for (Condutor c : listaCondutores) {
            qtdeSinistrosCondutor += c.getListaSinistros().size();
        }
        return CalcSeguro.VALOR_BASE.getValue() * CalcSeguro.FATOR_IDADE.calcularFator(idadeCliente) *
                (1 + 1/(clientePF.listarVeiculos().size() + 2)) * (2 + listaSinistros.size()/10) *
                (5 + qtdeSinistrosCondutor);
    }

    @Override
    public boolean desautorizarCondutor(String cpf) {
        for (Condutor c : listaCondutores) {
            if (c.getCPF().equals(cpf)) {
                // lógica de negócio
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean autorizarCondutor(String cpf) {
        for (Condutor c : listaCondutores) {
            if (c.getCPF().equals(cpf)) {
                // lógica de negócio
                return true;
            }
        }
        return false;
    }
}

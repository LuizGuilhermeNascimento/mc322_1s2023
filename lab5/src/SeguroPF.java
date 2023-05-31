
import java.util.Date;

public class SeguroPF extends Seguro {
    private Veiculo veiculo;
    private ClientePF clientePF;
    
    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente) {
        super(dataInicio, dataFim, seguradora, cliente);
        this.veiculo = veiculo;
        this.clientePF = cliente;
        this.valorMensal = calculaValor();
    }

    // getters e setters
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @Override
    public ClientePF getCliente() {
        return clientePF;
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
    public String toString() {
        return super.toString()+"\nCPF do cliente:"+this.clientePF.getCpf()+"\n"+veiculo.toString(); 
    }

}

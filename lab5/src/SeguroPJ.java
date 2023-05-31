import java.util.Calendar;
import java.util.Date;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ clientePJ;

    public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora, cliente);
        this.frota = frota;
        this.clientePJ = cliente;
        this.valorMensal = calculaValor();
    }

    @Override
    public ClientePJ getCliente() {
        return clientePJ;
    }
    

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    @Override
    public double calculaValor() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(clientePJ.getDataFundacao());
        int year = calendar.get(Calendar.YEAR);

        int qtdeSinistrosCondutor = 0;
        for (Condutor c : listaCondutores) {
            qtdeSinistrosCondutor += c.getListaSinistros().size();
        }

        return CalcSeguro.VALOR_BASE.getValue() * (10 + ( clientePJ.getQtdeFuncionarios() ) /10) *
                (1 + 1/( frota.getListaVeiculos().size() +2) ) *
                (1 + 1/( year +2) ) *
                (2 + listaSinistros.size() /10) *
                (5 + qtdeSinistrosCondutor /10);
    }

    @Override
    public String toString() {
        return super.toString()+"\nCNPJ do cliente:"+this.clientePJ.getCNPJ()+"\nCódigo da Frota: "+this.frota.getCode()+frota.toStringPlacaVeiculos(); 
    }

}

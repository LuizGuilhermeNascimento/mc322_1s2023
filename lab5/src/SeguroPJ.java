import java.util.Calendar;

public class SeguroPJ extends Seguro {
    private Frota frota;
    private ClientePJ clientePJ;

    public SeguroPF(Date dataInicio, Date dataFim, Seguradora seguradora, Frota frota, ClientePJ cliente) {
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.clientePJ = cliente;
    }


    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
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

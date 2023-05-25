import java.util.ArrayList;

public abstract class Seguro {
    private int final id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;
    private int valorMensal;
    private Cliente cliente;

    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, Cliente cliente) {
        this.id = gerarId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
        this.valorMensal = calculaValor();
        this.cliente = cliente;
    }

    // Função geradora de ID's
    public int gerarId() {
        Random r = new Random();
        return (r.nextInt(1000) * r.nextInt(1000));
    }


    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public Date getDataFim() {
        return dataFim;
    }
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public int getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }
    
    /**
     * @stub
     */
    public boolean desautorizarCondutor(String cpf) {
        return true;
    }

    /**
     * @stub
     */
    public boolean autorizarCondutor(String cpf) {
        return true;
    }
    /**
     * @stub
     */
    public double calculaValor() {
        return 0.0;
    }
    
    /**
     * Visualização do Sinistro de um Cliente
     * @return True se o cliente possui um sinistro, False senão
     */
    public String visualizarSinistro(String cliente) {
        StringBuilder sb = new StringBuilder();
        for (Sinistro s : listaSinistro) {
            if (s.getCliente().getDocumento().equals(cliente)) {
                sb.append(s.toString());
            }
        }
        if (!sb.isEmpty()) {
            return sb.toString();
        }
        return "\nO cliente não possui sinistros!\n";
    }

        /**
     * Transforma uma ArrayList de sinistros em uma String
     * @return String
     */
    public String toStringListaSinistro() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaSinistro.size(); i++) {
            sb.append("\n----- Sinistro "+(i+1)+" ----\n");
            sb.append(listaSinistro.get(i).toString()+"\n");
        }
        return sb.toString();
    }


    public boolean gerarSinistro(Date data, String endereco, String cpf) {
        Condutor condutorSinistro = null;
        for (Condutor c : listaCondutores) {
            if (c.getCPF().equals(cpf)) {
                condutorSinistro = c;
                break;
            }
        }
        return listaSinistros.add(new Sinistro(data, endereco, condutorSinistro, this));
    }
}

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public abstract class Seguro {
    private final int id;
    private Date dataInicio;
    private Date dataFim;
    private Seguradora seguradora;
    protected ArrayList<Sinistro> listaSinistros;
    protected ArrayList<Condutor> listaCondutores;
    protected double valorMensal;
    private Cliente cliente;

    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, Cliente cliente) {
        this.id = gerarId();
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaCondutores = new ArrayList<Condutor>();
        this.cliente = cliente;
    }

    // Função geradora de ID's
    public int gerarId() {
        Random r = new Random();
        return (r.nextInt(1000) * r.nextInt(1000));
    }

    // getters e setters
    public Cliente getCliente() {
        return cliente;
    }
    public int getId() {
        return id;
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
    public double getValorMensal() {
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
     * Transforma uma ArrayList de sinistros em uma String
     * @return String
     */
    public String toStringListaSinistro() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaSinistros.size(); i++) {
            sb.append("\n----- Sinistro "+(i+1)+" ----\n");
            sb.append(listaSinistros.get(i).toString()+"\n");
        }
        return sb.toString();
    }

    /**
     * Transforma uma lista de id de sinistros em uma String
     */
    public String toStringIdSinistros() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID's do sinistros:\n");
        for (int i = 0; i < listaSinistros.size(); i++) {
            sb.append(listaSinistros.get(i).getId() + "\n");
        }
        return sb.toString();
    }

    /**
     * Transforma uma lista de cpf de condutores em uma String
     */
    public String toStringCpfCondutores() {
        StringBuilder sb = new StringBuilder();
        sb.append("CPF dos condutores:\n");
        for (int i = 0; i < listaCondutores.size(); i++) {
            sb.append(listaCondutores.get(i).getCPF() + "\n");
        }
        return sb.toString();
    }


    public boolean gerarSinistro(Date data, String endereco, String cpf) {
        Condutor condutorSinistro = null;
        for (Condutor c : listaCondutores) {
            if (c.getCPF().equals(cpf)) {
                condutorSinistro = c;
                return listaSinistros.add(new Sinistro(data, endereco, condutorSinistro, this));
            }
        }
        return false;
    }
    /**
     * Adiciona um condutor recebendo um objeto Condutor
     */
    public boolean adicionarCondutor(Condutor condutor) {
        return listaCondutores.add(condutor);
    }

    /**
     * Remove um condutor recebendo o documento do condutor
     */
    public boolean removerCondutor(String documentoCondutor) {
        for (int i = 0; i < listaCondutores.size(); i++) {
            if (listaCondutores.get(i).getCPF().equals(documentoCondutor)) {
                listaCondutores.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um sinistro baseado no seu id
     */
    public boolean removerSinistroPorId(int id) {
        for (int i = 0; i < listaSinistros.size(); i++) {
            if (listaSinistros.get(i).getId() == id) {
                listaSinistros.remove(i);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "ID: "+this.id+"Data de início: "+this.dataInicio+"\nData de Fim: "+this.dataFim+"\nNome da seguradora: "+this.seguradora.getNome()+
        toStringIdSinistros()+toStringCpfCondutores()+"\nValor Mensal: "+this.valorMensal;
    }
}

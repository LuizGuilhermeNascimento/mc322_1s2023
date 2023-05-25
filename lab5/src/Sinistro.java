import java.util.Random;
import java.util.Date;

public class Sinistro {
    
    private final int id;
    private Date data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;


    // Função geradora de ID's
    public int gerarId() {
        Random r = new Random();
        return (r.nextInt(1000) * r.nextInt(1000));
    }
    // construtor
    public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

    // getters e setters
    public int getId() {
        return id;
    }
    public Date getData() {
        return data;
    }
    public String getEndereco() {
        return endereco;
    }

    public void setData(Date data) {
        this.data = data;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Condutor getCondutor() {
        return condutor;
    }
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
    public Seguro getSeguro() {
        return seguro;
    }
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String toString() {
        return "Data: " + this.data.toString() + "\nEndereço: " + this.endereco + "\nSeguradora: " + this.seguradora.getNome() +
        "\nVeículo: " + this.veiculo.getPlaca() + "\nDocumento do Cliente: " + this.cliente.getDocumento();
    }
    
}

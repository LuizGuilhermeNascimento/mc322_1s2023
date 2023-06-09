import java.util.Random;
import java.util.Date;

public class Sinistro {
    
    private final int id;
    private Date data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    // construtor


    // Função geradora de ID's
    public int gerarId() {
        Random r = new Random();
        return (r.nextInt(1000) * r.nextInt(1000));
    }

    public Sinistro(Date data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
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

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String toString() {
        return "Data: " + this.data.toString() + "\nEndereço: " + this.endereco + "\nSeguradora: " + this.seguradora.getNome() +
        "\nVeículo: " + this.veiculo.getPlaca() + "\nDocumento do Cliente: " + this.cliente.getDocumento();
    }
    
}

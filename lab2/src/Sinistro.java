import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    public Sinistro(String data, String endereco) {

        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
    }

    // Função geradora de ID's
    private int gerarId() {
        Random r = new Random();
        return r.nextInt(1000000);
    }

    public int getId() {
        return id;
    }
    public String getData() {
        return data;
    }
    public String getEndereco() {
        return endereco;
    }

    public void setData(String data) {
        this.data = data;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    
    
}

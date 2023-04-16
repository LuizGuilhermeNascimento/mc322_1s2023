import java.util.ArrayList;

public class Cliente {
    
    protected String nome;
    protected String endereco;
    
    private ArrayList<Veiculo> listaVeiculos;

    // construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        listaVeiculos = new ArrayList<Veiculo>();
    }

    // getters e setters
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String listaVeiculosToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nLista de veículos:");
        int i = 1;
        for (Veiculo c : this.listaVeiculos) {
            sb.append("\n--- Veículo "+(i)+" ---");
            sb.append("\n" + c.toString());
        }
        return sb.toString();
    }

    public String getDocumento() {
        return null;
    }

    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (veiculo == null) { return false; }
        listaVeiculos.add(veiculo);
        return true;
    }

    public boolean removerVeiculo(Veiculo veiculo) {
        if (veiculo == null) { return false; }
        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (listaVeiculos.get(i) != null && listaVeiculos.get(i).getPlaca().equals(veiculo.getPlaca())) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return true;
    }

    public String toString() {
        String clienteString = "Nome: " + this.nome + "\nEndereço: " + this.endereco+listaVeiculosToString();
        return clienteString;
    }
}

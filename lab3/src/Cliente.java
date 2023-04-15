import java.util.List;

public class Cliente {
    
    protected String nome;
    protected String endereco;
    
    private List<Veiculo> listaVeiculos;

    // construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public Cliente(String nome, String endereco, List<Veiculo> listaVeiculos) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
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
        sb.append("\nLista de veículos:");
        for (Veiculo c : this.listaVeiculos) {
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

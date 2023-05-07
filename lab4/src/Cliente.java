import java.util.ArrayList;

public class Cliente {
    
    protected String nome;
    protected String endereco;
    
    protected ArrayList<Veiculo> listaVeiculos;
    protected double valorSeguro;

    // construtor
    public Cliente(String nome, String endereco) {
        if (Validacao.validarNome(nome)) {
            this.nome = nome;
        } else {
            this.nome = "nome inválido!";
        }
        
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
    public double getValorSeguro() {
        return this.valorSeguro;
    }
    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public void setListaVeiculos(ArrayList<Veiculo> novosVeiculos) {
        this.listaVeiculos = novosVeiculos;
    }
    
    /**
     * Transforma uma ArrayList de clientes em String
     * @return String contendo as informações dos veículos do cliente
     */
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

    /**
     * @stub
     */
    public String getDocumento() {
        return null;
    }

    /**
     * Cadastra um veículo no cliente
     * @return True se o cadastro for concluído com sucesso, False senão
     */
    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (veiculo == null) { return false; }
        listaVeiculos.add(veiculo);
        return true;
    }

    /**
     * Remove um veículo cadastrado no veículo
     * @return True se a remoção for concluída com sucesso, False senão
     */
    public boolean removerVeiculo(String placaVeiculo) {

        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (listaVeiculos.get(i) != null && listaVeiculos.get(i).getPlaca().equals(placaVeiculo)) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Listagem dos veículos
     * @return
     */
    public ArrayList<Veiculo> listarVeiculos() {
        return this.listaVeiculos;
    }

    public String toString() {
        String clienteString = "Nome: " + this.nome + "\nEndereço: " +this.endereco+ "\nValor do Seguro: "+this.valorSeguro+listaVeiculosToString();
        return clienteString;
    }

    /**
     * @stub
     */
    public double calculaScore() {
        return 0.0;
    };
}

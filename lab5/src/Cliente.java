import java.util.ArrayList;

public abstract class Cliente {
    
    protected String nome;
    protected String endereco;

    // construtor
    public Cliente(String nome, String endereco) {
        if (Validacao.validarNome(nome)) {
            this.nome = nome;
        } else {
            this.nome = "nome inválido!";
        }
        
        this.endereco = endereco;
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

    /**
     * Lista todos os veículos cadastrados no cliente
     * Caso seja PF, retorna a array de veículos
     * Caso seja PJ, retorna um array com todos os veículos de todas as frotas
     */
    public ArrayList<Veiculo> listaVeiculosCadastrados() {
        return null;
    }

    /**
     * @stub
     */
    public String getDocumento() {
        return null;
    }

    public String toString() {
        String clienteString = "Nome: " + this.nome + "\nEndereço: " +this.endereco;
        return clienteString;
    }

}

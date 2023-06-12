import java.util.HashMap;
/**
 * Classe utilizada para armazenar as seguradoras (pressupondo que mais de uma seguradora pode ser cadastrada),
 * permitindo com que elas sejam acessadas rapidamente por meio de um hash
 */
public class BancoDeSeguradoras {
    private HashMap<String, Seguradora> hash;

    public BancoDeSeguradoras() {
        this.hash = new HashMap<String, Seguradora>();
    }

    public boolean contemSeguradora(String cnpjSeguradora) {
        return hash.containsKey(cnpjSeguradora);
    }
    /**
     * Atualiza uma seguradora no hash
     * Caso a seguradora já esteja armazenada, atualiza o objeto Seguradora relativo
     * Caso não esteja, adiciona no hash
     */
    public void updateSeguradora(Seguradora seguradora) {
        hash.put(seguradora.getCNPJ(), seguradora);
    }

    public Seguradora getSeguradora(String cnpjSeguradora) {
        return hash.get(cnpjSeguradora);
    } 

}

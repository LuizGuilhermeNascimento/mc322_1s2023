import java.util.HashMap;

public class BancoDeSeguradoras {
    private HashMap<String, Seguradora> hash;

    public BancoDeSeguradoras() {
        this.hash = new HashMap<String, Seguradora>();
    }

    public boolean contemSeguradora(String nomeSeguradora) {
        return hash.containsKey(nomeSeguradora);
    }

    public void updateSeguradora(Seguradora seguradora) {
        hash.put(seguradora.getNome(), seguradora);
    }

    public Seguradora getSeguradora(String nomeSeguradora) {
        return hash.get(nomeSeguradora);
    } 

}

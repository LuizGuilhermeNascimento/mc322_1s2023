import java.util.ArrayList;
import java.util.UUID;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos;

    public Frota() {
        this.code = geradorCode();
        this.listaVeiculos = new ArrayList<>();
    }

    public String geradorCode() {
        UUID idGerada = UUID.randomUUID();
        return idGerada.toString();
    }

    // getters e setters
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }
    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public boolean addVeiculo(Veiculo veiculo) {
        for (Veiculo v : listaVeiculos) {
            if (veiculo.getPlaca().equals(v.getPlaca())) {
                return false;
            }
        }
        listaVeiculos.add(veiculo);
        return true;
    }

    public boolean removeVeiculo(String placaVeiculo) {
        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (listaVeiculos.get(i).getPlaca().equals(placaVeiculo)) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return false;
    }

    public String toStringPlacaVeiculos() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nPlaca dos veículos:\n");
        for (int i = 0; i < listaVeiculos.size(); i++) {
            sb.append(listaVeiculos.get(i).getPlaca() + "\n");
        }
        return sb.toString();
    }

    public String toStringListaVeiculos() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de veículos:\n");
        for (int i = 0; i < listaVeiculos.size(); i++) {
            sb.append("\n----- Veículo "+(i+1)+" ----\n");
            sb.append(listaVeiculos.get(i).toString() + "\n");
        }
        return sb.toString();
    }

    public String toString() {
        return "Código: "+this.code+toStringListaVeiculos();
    }

}

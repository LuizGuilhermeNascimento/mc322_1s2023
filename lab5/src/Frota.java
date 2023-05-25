import java.util.ArrayList;

public class Frota {
    private String code;
    private ArrayList<Veiculo> listaVeiculos;

    public Frota(String code, ArrayList<Veiculo> listaVeiculos) {
        this.code = code;
        this.listaVeiculos = listaVeiculos;
    }

    // getters e setters
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    // TODO
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

    public boolean removeVeiculo(Veiculo veiculo) {
        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (listaVeiculos.get(i).getPlaca().equals(v.getPlaca())) {
                listaVeiculos.remove(i);
                return true;
            }
        }
        return false;
    }
}

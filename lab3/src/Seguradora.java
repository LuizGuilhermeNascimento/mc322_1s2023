import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.Date;

public class Seguradora {
    
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistro;
    private ArrayList<Cliente> listaClientes;
    
    // construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        listaSinistro = new ArrayList<Sinistro>();
        listaClientes = new ArrayList<Cliente>();
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (cliente == null) { return false; }
        this.listaClientes.add(cliente);
        return true;
    }

    public boolean removerCliente(String cliente) {
        for (int i = 0; i < listaClientes.size(); i++) {
            if (listaClientes.get(i) != null && listaClientes.get(i).getDocumento().equals(cliente)) {
                listaClientes.remove(i);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Cliente> listarClientes(String tipoCliente) {

        ArrayList<Cliente> clientesArray = new ArrayList<>();
        for (Cliente c : listaClientes) {
            if ((tipoCliente.equals("PF") && c instanceof ClientePF) || (tipoCliente.equals("PJ") && c instanceof ClientePJ)) {
                clientesArray.add(c);
            }
        }
        return clientesArray;

    }

    public boolean gerarSinistro(Date data, String endereco, Veiculo veiculo, Cliente cliente) {
        return listaSinistro.add(new Sinistro(data,endereco,this,veiculo,cliente));
    }

    public boolean visualizarSinistro(String cliente) {
        boolean existeSinistro = false;
        for (Sinistro s : listaSinistro) {
            if (s.getCliente().getDocumento().equals(cliente)) {
                System.out.println(s.toString());
                existeSinistro = true;
            }
        }
        return existeSinistro;
    }

    public ArrayList<Sinistro> listarSinistros() {
        return listaSinistro;
    }
    private String toStringListaClientes() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaClientes.size(); i++) {
            sb.append("\n----- Cliente "+(i+1)+" ----\n");
            sb.append(listaClientes.get(i).toString()+"\n");
        }
        return sb.toString();
    }
    private String toStringListaSinistro() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listaSinistro.size(); i++) {
            sb.append("\n----- Sinistro "+(i+1)+" ----\n");
            sb.append(listaSinistro.get(i).toString()+"\n");
        }
        return sb.toString();
    }
    
    public String toString() {
        return "Nome: "+this.nome+"\nTelefone: "+this.telefone+"\nEmail: "+this.email+"\nEndereço: "+this.endereco+
        "\nLista de Clientes: "+toStringListaClientes()+"\nLista de Sinistros: "+toStringListaSinistro();
    }
}

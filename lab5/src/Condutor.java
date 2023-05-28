import java.util.ArrayList;
import java.util.Date;

public class Condutor {
    
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private Date dataNascimento;
    private ArrayList<Sinistro> listaSinistros;

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
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public String getCPF() {
        return cpf;
    }

    public Condutor(String cpf, String nome, String telefone, String endereco, String email, Date dataNascimento) {

        if (Validacao.validarCPF(cpf)) {
            this.cpf = cpf;
        } else { this.cpf = "CPF inválido!"; }
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinistros = new ArrayList<Sinistro>();
    }

    public boolean adicionarSinistro(Date data, String endereco, Veiculo veiculo, Seguro seguro) {
        listaSinistros.add(new Sinistro(data, endereco, this, seguro));
        return true;
    }

    public boolean adicionarSinistro(Sinistro sinistro) {
        listaSinistros.add(sinistro);
        return true;
    }

    public boolean removerSinistro(int id) {
        for (Sinistro s : listaSinistros) {
            if (s.getId() == id) {
                listaSinistros.remove(id);
                return true;
            }
        }
        return false;
    }

    public String toStringListaSinistros() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nLista de sinistros: \n");
        for (Sinistro s : listaSinistros){
            sb.append(s.toString()+"\n");
        }
        return sb.toString();
    }

    public String toString() {
        return "\nCPF: "+this.cpf+"\nNome: "+this.nome+"\nTelefone: "+this.telefone+"\nEndereço: "+this.endereco+"\nData de nascimento: "+
        this.dataNascimento.toString()+toStringListaSinistros();
    }
}

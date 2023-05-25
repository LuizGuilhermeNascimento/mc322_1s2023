import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ClientePF extends Cliente {

    private String educacao;
    private String genero;
    private final String cpf;
    private Date dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    public ClientePF(String nome , String endereco ,
            String educacao , String genero, String cpf, Date dataNascimento) {

        super( nome , endereco);
        if (Validacao.validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            this.cpf = "CPF inválido";
        }
        this.educacao = educacao;
        this.genero = genero;
        this.dataNascimento = dataNascimento;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int calcularIdade(Date dataNascimento) {
        Date data_atual = new Date();
        // calculando a diferença entre as datas em milissegundos
        long diferencaMS = data_atual.getTime() - dataNascimento.getTime();
        // converte-se a diferença de milissegundos em minutos
        long diff = TimeUnit.MINUTES.convert(diferencaMS, TimeUnit.MILLISECONDS);
        // retorna a diferença convertida em anos
        return (int)(diff / 525600);
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

    public getVeiculoPorPlaca(String placa) {
        for (Veiculo v : listaVeiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }
    
    /**
     * Retorna o documento do cliente
     */
    @Override
    public String getDocumento() {
        return this.cpf;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + "\nEndereço: " + this.endereco+"\nEducação: " + this.educacao + "\nGênero: " + this.genero+
        "\nCPF: "+this.cpf+"\nData de nascimento: "+this.dataNascimento+ "\nValor do Seguro: "+this.valorSeguro+listaVeiculosToString();
    }

    /**
     * Calcula o score do cliente baseado na fórmula fornecida
     */
    @Override
    public double calculaScore() {
        int idadeCliente = calcularIdade(this.dataNascimento);

        return CalcSeguro.VALOR_BASE.getValue() * CalcSeguro.FATOR_IDADE.calcularFator(idadeCliente) * this.listaVeiculos.size();
    };
}

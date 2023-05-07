import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ClientePF extends Cliente{
    private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;
    private final String cpf;
    private Date dataNascimento;

    public ClientePF(String nome , String endereco , Date dataLicenca,
            String educacao , String genero , String classeEconomica,
            String cpf, Date dataNascimento) {

        super( nome , endereco);
        if (Validacao.validarCPF(cpf)) {
            this.cpf = cpf;
        } else {
            this.cpf = "CPF inválido";
        }
        this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.dataNascimento = dataNascimento;
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
    
    private int calcularIdade(Date dataNascimento) {
        Date data_atual = new Date();
        // calculando a diferença entre as datas em milissegundos
        long diferencaMS = data_atual.getTime() - dataNascimento.getTime();
        // converte-se a diferença de milissegundos em minutos
        long diff = TimeUnit.MINUTES.convert(diferencaMS, TimeUnit.MILLISECONDS);
        // retorna a diferença convertida em anos
        return (int)(diff / 525600);
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
        return "Nome: " + this.nome + "\nEndereço: " + this.endereco + "\nData de Licença: " + this.dataLicenca.toString() +
        "\nEducação: " + this.educacao + "\nGênero: " + this.genero+ "\nClasse Econômica: " + this.classeEconomica+"\nCPF: "+
        this.cpf+"\nData de nascimento: "+this.dataNascimento+ "\nValor do Seguro: "+this.valorSeguro+listaVeiculosToString();
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

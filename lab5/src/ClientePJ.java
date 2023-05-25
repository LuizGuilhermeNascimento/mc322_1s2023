import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente{
    private final String cnpj;
    private Date dataFundacao;
    private ArrayList<Frota> listaFrotas;
    private int qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, String cnpj, Date dataFundacao, int qtdeFuncionarios) {

        super( nome , endereco);
        if (Validacao.validarCNPJ(cnpj)) {
            this.cnpj = cnpj;
        } else {
            this.cnpj = "CNPJ inválido";
        }
        this.dataFundacao = dataFundacao;
        this.listaFrotas = new ArrayList<Frota>();
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    
    public int getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(int qtdeFuncionarios) {
        this.qtdeFuncionarios = qtdeFuncionarios;
    }

    public String getCNPJ() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public boolean cadastrarFrota(Frota frota) {
        for (Frota f : listaFrotas) {
            if (frota.getCode().equals(f.getCode())) {
                return false;
            }
        }
        listaFrotas.add(frota);
        return true;
    }

    public boolean atualizarFrota(String code, FrotaOperacoes operacao, Veiculo veiculo) {
        for (Frota f : listaFrotas) {
            if (frota.getCode().equals(code)) {
                switch(operacao) {
                    case FrotaOperacoes.ADICIONAR_VEICULO:
                        f.addVeiculo(veiculo);
                    case FrotaOperacoes.REMOVER_VEICULO:
                        f.removeVeiculo(veiculo);
                }
            }
        }
        return false;
    }

    public boolean atualizarFrota(String code, FrotaOperacoes operacao) {
        int to_remove;
        for (int i = 0; i < listaFrotas.size(); i++) {
            if (listaFrotas.get(i).getCode().equals(code)) {
                if (operacao.equals(FrotaOperacoes.REMOVER_FROTA)) {
                    to_remove = i;
                    break;
                }
            }
        }
        return listaFrotas.remove(to_remove);
    }

    public ArrayList<Veiculo> getVeiculosPorFrota(String code) {
        for (Frota f : listaFrotas) {
            if (frota.getCode().equals(code)) {
                return f.getListaVeiculos();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Nome: "+this.nome +"\nEndereço: "+this.endereco +"\nCNPJ: "+this.cnpj+"\nData de fundação: "+this.dataFundacao +
        "\nValor do Seguro: "+this.valorSeguro+"\nQuantidade de funcionários: "+this.qtdeFuncionarios+ listaVeiculosToString();
    }

    /**
     * Retorna o documento do cliente
     */
    @Override
    public String getDocumento() {
        return this.cnpj;
    }

    /**
     * Calcula o score do cliente baseado na fórmula fornecida
     */
    @Override
    public double calculaScore() {
        return CalcSeguro.VALOR_BASE.getValue() * (1+ (double)(this.qtdeFuncionarios)/100) * this.listaVeiculos.size();
    };
}

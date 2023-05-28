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
            if (f.getCode().equals(code)) {
                switch(operacao) {
                    case ADICIONAR_VEICULO:
                        f.addVeiculo(veiculo);
                    default:
                        break;
                }
            }
        }
        return false;
    }

    public boolean atualizarFrota(String code, FrotaOperacoes operacao, String placaVeiculo) {
        for (Frota f : listaFrotas) {
            if (f.getCode().equals(code)) {
                switch(operacao) {
                    case REMOVER_VEICULO:
                        f.removeVeiculo(placaVeiculo);
                    default:
                        break;
                }
            }
        }
        return false;
    }


    public boolean atualizarFrota(String code, FrotaOperacoes operacao) {

        for (int i = 0; i < listaFrotas.size(); i++) {
            if (listaFrotas.get(i).getCode().equals(code)) {
                if (operacao.equals(FrotaOperacoes.REMOVER_FROTA)) {
                    listaFrotas.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Veiculo> getVeiculosPorFrota(String code) {
        for (Frota f : listaFrotas) {
            if (f.getCode().equals(code)) {
                return f.getListaVeiculos();
            }
        }
        return null;
    }

    public ArrayList<Frota> listarFrotas() {
        return listaFrotas;
    }

    @Override
    public ArrayList<Veiculo> listaVeiculosCadastrados() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        for (Frota f : listaFrotas) {
            veiculos.addAll(f.getListaVeiculos());
        }
        return veiculos;
    }

    public String toStringCodeFrota() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nCódigos das frotas:\n");
        for (int i = 0; i < listaFrotas.size(); i++) {
            sb.append(listaFrotas.get(i).getCode() + "\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Nome: "+this.nome +"\nEndereço: "+this.endereco +"\nCNPJ: "+this.cnpj+"\nData de fundação: "+this.dataFundacao +
        "\nQuantidade de funcionários: "+this.qtdeFuncionarios+toStringCodeFrota();
    }

    /**
     * Retorna o documento do cliente
     */
    @Override
    public String getDocumento() {
        return this.cnpj;
    }
}

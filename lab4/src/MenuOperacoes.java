import java.util.ArrayList;
import java.util.Date;

public enum MenuOperacoes {
    CADASTRAR (1),
    LISTAR(2),
    EXCLUIR (3),
    GERAR_SINISTRO(4),
    TRASNFERIR_SEGURO(5),
    CALCULAR_RECEITA_SEGURADORA(6),
    VERIFICACAO(7),
    SAIR (0);

    public final int operacao;

    MenuOperacoes ( int operacao ) {
        this.operacao = operacao;
    }

    /////////////// OPERAÇÕES DE CADASTRO ///////////////

    public boolean cadastrarCliente(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora, Cliente cliente) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                return seg.cadastrarCliente(cliente);
            }
        }
        return false;
    }

    public boolean cadastrarVeiculo(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora, String clienteDocumento, Veiculo veiculo) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                ArrayList<Cliente> listaClientes = seg.listarClientes();
                for (int i = 0; i < listaClientes.size(); i++) {
                    Cliente atuaCliente = listaClientes.get(i);
                    if (atuaCliente.getDocumento().equals(clienteDocumento)) {
                        Cliente novoCliente = atuaCliente;
                        novoCliente.cadastrarVeiculo(veiculo);
                        seg.removerCliente(clienteDocumento);
                        seg.cadastrarCliente(novoCliente);
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean cadastrarSeguradora(ArrayList<Seguradora> listaSeguradoras, Seguradora seguradora) {

        for (Seguradora seg : listaSeguradoras) {
            if (seg.equals(seguradora)) {
                return false;
            }
        }
        listaSeguradoras.add(seguradora);
        return true;
    }

    /////////////// OPERAÇÕES DE LISTAGEM ///////////////

    public String listarClientesPorSeguradora(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                if (seg.listarClientes().size() == 0) {
                    return "A seguradora não possui clientes cadastrados!";
                }
                StringBuilder sg = new StringBuilder();
                sg.append("Clientes da Seguradora "+seg.getNome()+"\n");
                sg.append(seg.toStringListaClientes());
                return sg.toString();
            }
        }
        return "Seguradora não encontrada!";
    }

    public String listarSinistrosPorSeguradora(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                if (seg.listarSinistros().size() == 0) {
                    return "A seguradora não possui sinistros cadastrados!";
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Sinistros da Seguradora "+seg.getNome()+"\n");
                sb.append(seg.toStringListaSinistro());
                return sb.toString();
            }
        }
        return "Seguradora não encontrada!";
    }
    public String listarSinistroPorCliente(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora, String clienteDocumento) {
        StringBuilder sb = new StringBuilder();
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                sb.append("Sinistro(s) registrado(s) no documento "+clienteDocumento+"\n");
                sb.append(seg.visualizarSinistro(clienteDocumento));
                return sb.toString();
            }
        }
        return "Seguradora não encontrada!";
    }
    public String listarVeiculoPorCliente(ArrayList<Seguradora> listaSeguradoras, String clienteDocumento) {
        StringBuilder sb = new StringBuilder();

        for (Seguradora seg : listaSeguradoras) {
            Cliente cliente_seg = seg.procurarClientePorDocumento(clienteDocumento);
            if (cliente_seg.listarVeiculos().size() == 0) {
                return "O cliente não possui veículos cadastrados!";
            }
            if (cliente_seg != null) {
                sb.append("Veículo(s) registrado(s) no documento "+clienteDocumento+"\n");
                sb.append(cliente_seg.listaVeiculosToString());
                return sb.toString();
            }
        }
        return "Cliente não encontrado!";
    }

    public String listarVeiculoPorSeguradora(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora) {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de veículos: \n");
        int i = 0;
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                for (Cliente c : seg.listarClientes()) {
                    for (Veiculo v : c.listarVeiculos()) {
                        i++;
                        sb.append("\n--- Veículo "+(i)+" ---");
                        sb.append(v.toString());
                    }
                }
            }
        }
        if (i == 0) {
            sb.append("\nA seguradora não possui veículos cadastrados!\n");
        }
        return sb.toString();
    }

    /////////////// OPERAÇÕES DE EXCLUSÃO ///////////////

    public boolean excluirCliente(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora, String clienteDocumento) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
               return seg.removerCliente(clienteDocumento);
            }
        }
        return false;
    }

    public boolean excluirVeiculo(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora, String clienteDocumento, String placaVeiculo) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                ArrayList<Cliente> listaClientes = seg.listarClientes();
                for (int i = 0; i < listaClientes.size(); i++) {
                    Cliente atualCliente = listaClientes.get(i);
                    if (atualCliente.getDocumento().equals(clienteDocumento)) {
                        atualCliente.removerVeiculo(placaVeiculo);
                        seg.removerCliente(clienteDocumento);
                        seg.cadastrarCliente(atualCliente);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean excluirSinistro(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora, int idSinistro) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                return seg.excluirSinistro(idSinistro);
            }
        }
        return false;
    }

    public boolean gerarSinistro(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora, String clienteDocumento, Veiculo veiculo) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                return seg.gerarSinistro(new Date(), nomeSeguradora, veiculo, seg.procurarClientePorDocumento(clienteDocumento));
            }
        }
        return false;
    }

    public boolean transferirSeguro(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora, String clienteFonteDocumento, String clienteDestinoDocumento) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                return seg.transferirSeguro(clienteFonteDocumento, clienteDestinoDocumento);
            }
        }
        return false;
    }

    public double calcularReceitaSeguradora(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                return seg.calcularReceita();
            }
        }
        return 0.0;
    }
    
    public boolean existeSeguradora(ArrayList<Seguradora> listaSeguradoras, String nomeSeguradora) {
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getNome().equals(nomeSeguradora)) {
                return true;
            }
        }
        return false;
    }

    public int getOperacao () {
        return this.operacao ;
    }
}

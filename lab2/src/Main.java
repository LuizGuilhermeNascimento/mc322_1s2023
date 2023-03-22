public class Main {
    public static void main(String[] args) throws Exception {

        // Instanciação dos Objetos
        // O objeto cliente está sendo instanciado com um CPF válido (obtido a partir de um gerador de cpf online)
        Cliente cliente = new Cliente("Luiz", "435.102.440-28", "02/02/2004", 18, "Rua Jose Freire");
        Sinistro sinistro = new Sinistro("02/02/2004", "Rua Jose Freire");
        Veiculo veiculo = new Veiculo("ABC1D23", "marca", "modelo");
        Seguradora seguradora = new Seguradora("nome_seguradora", "(11)01234-5678", "emailficticio@gmail.com", "Rua Jose Freire");

        // Testes das funções
        System.out.println("TESTE DA FUNÇÃO validarCPF()");
        System.out.println("Para o CPF 000.000.000-00: " + cliente.validarCPF("000.000.000-00"));
        System.out.println("Para o CPF 435.102.440-28: " + cliente.validarCPF("435.102.440-28"));

        System.out.println("\n--------------------------------------------------------------------\n");
        System.out.println("GETTERS E SETTERS DA CLASSE CLIENTE");
        System.out.println("Antes do setNome(): " + cliente.getNome());
        cliente.setNome("nome alterado");
        System.out.println("Depois do setNome(): " + cliente.getNome());

        System.out.println("\n--------------------------------------------------------------------\n");
        System.out.println("GETTERS E SETTERS DA CLASSE SINISTRO");
        System.out.println("Antes do setData(): " + sinistro.getData());
        sinistro.setData("12/12/2012");
        System.out.println("Depois do setData(): " + sinistro.getData());
        System.out.println("\nTESTE DA FUNÇÃO GERADORA DE ID's");
        System.out.println("Id gerada: " + sinistro.gerarId());

        System.out.println("\n--------------------------------------------------------------------\n");
        System.out.println("GETTERS E SETTERS DA CLASSE VEÍCULO");
        System.out.println("Antes do setPlaca(): " + veiculo.getPlaca());
        veiculo.setPlaca("EFG3H45");
        System.out.println("Depois do setPlaca(): " + veiculo.getPlaca());

        System.out.println("\n--------------------------------------------------------------------\n");
        System.out.println("GETTERS E SETTERS DA CLASSE SEGURADORA");
        System.out.println("Antes do setNome(): " +seguradora.getNome());
        seguradora.setNome("nome alterado");
        System.out.println("Depois do setNome(): " + seguradora.getNome());
    }
}

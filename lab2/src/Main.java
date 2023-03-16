public class Main {
    public static void main(String[] args) throws Exception {

        // Instanciação dos Objetos
        Cliente cliente = new Cliente("Luiz", "435.102.440-28", "02/02/2004", 18, "Rua Jose Freire");
        Sinistro sinistro = new Sinistro("02/02/2004", "Rua Jose Freire");
        Veiculo veiculo = new Veiculo("ABC1D23", "marca", "modelo");
        Seguradora seguradora = new Seguradora("nome_seguradora", "(11)01234-5678", "emailficticio@gmail.com", "Rua Jose Freire");
        
    }
}

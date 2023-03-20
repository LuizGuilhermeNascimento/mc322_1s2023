public class Main {
    public static void main(String[] args) throws Exception {

        // Instanciação dos Objetos
        Cliente cliente = new Cliente("Cliente", "435.102.440-28", "99/99/9999", 18, "endereço");
        Sinistro sinistro = new Sinistro("99/99/9999", "endereço");
        Veiculo veiculo = new Veiculo("ABC1D23", "marca", "modelo");
        Seguradora seguradora = new Seguradora("Seguradora", "(11)01234-5678", "emailficticio@gmail.com", "endereço");
        
    }
}

package cnpj;

public class ExemploUtilizacaoCnpj {
    
    public static void main(String[] args) {
    	
        CnpjAlfa cnpjAlfa = new CnpjAlfa();

        // CNPJs Para Testes

        String cnpj_teste;
        
        // cnpj_teste = "62.612.161/4838-01";      // Correto
        // cnpj_teste = "34.183.784/0001-52";      // Correto
        // cnpj_teste = "34.183.784/000%-52";      // CNPJ Inválido
        // cnpj_teste = "34.183.784/0001-50";      // Digto Inválido
        // cnpj_teste = "34.183.784/0001-42";      // Digto Inválido
        // cnpj_teste = "7U.R9E.BBF/CTAU-77";      // Correto
        // cnpj_teste = "7U.R9E.BBF/CTAU-70";      // Digito Inválido
        // cnpj_teste = "7U.R9E.BBF/0000-77";      // Digito Inválido
        // cnpj_teste = "%%.R9E.BBF/CTAU-77";      // CNPJ Inválido
        // cnpj_teste = "7U.R@E.BBF/@TAU-77";      // CNPJ Inválido        

        
        // Teste de validação de CNPJ Alfanumerico
        cnpj_teste = "7U.R9E.BBF/CTAU-77";
        try {
            boolean valido = cnpjAlfa.ehValido(cnpj_teste);
            String status = valido ? "VALIDO" : "INVÁLIDO";
            System.out.println("Teste de Validação -> CNPJ: " + cnpj_teste + " - " + status + "!");
        } catch (IllegalArgumentException e) {
            System.out.println("Teste de Validação -> Erro: " + e.getMessage());
        }
       
        // Teste de validação de CNPJ Alfanumerico
        cnpj_teste = "62.612.161/4838-01";
        try {
            boolean valido = cnpjAlfa.ehValido(cnpj_teste);
            String status = valido ? "VALIDO" : "INVÁLIDO";
            System.out.println("Teste de Validação -> CNPJ: " + cnpj_teste + " - " + status + "!");
        } catch (IllegalArgumentException e) {
            System.out.println("Teste de Validação -> Erro: " + e.getMessage());
        }
       
		// Teste de geração de CNPJ Alfanumerico
		try {
		    String cnpjGerado = cnpjAlfa.geraCnpj(cnpj.ConstantesTipoCnpj.ALFANUMERICO);
		    System.out.println("Teste de Geração de CNPJ Alfanumerico -> CNPJ: " + cnpjGerado + " - Gerado Com Sucesso!");
		} catch (IllegalArgumentException e) {
		    System.out.println("Teste de Geração de CNPJ Alfanumerico -> Erro: " + e.getMessage());
		}        

		// Teste de geração de CNPJ Numerico
		try {
		    String cnpjGerado = cnpjAlfa.geraCnpj(cnpj.ConstantesTipoCnpj.NUMERICO);
		    System.out.println("Teste de Geração de CNPJ Numerico -> CNPJ: " + cnpjGerado + " - Gerado Com Sucesso!");
		} catch (IllegalArgumentException e) {
		    System.out.println("Teste de Geração de CNPJ Nuemrico -> Erro: " + e.getMessage());
		}
		
    }
}
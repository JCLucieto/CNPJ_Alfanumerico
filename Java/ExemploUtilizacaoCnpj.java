package cnpj;

public class ExemploUtilizacaoCnpj {
    
    public static void main(String[] args) {
    	
        CnpjAlfa cnpjAlfa = new CnpjAlfa();

        // CNPJs Para Testes

        String cnpj_teste;
        
        // cnpj_teste = "62.612.161/4838-01";      // Correto
        // cnpj_teste = "34.183.784/0001-52";      // Correto
        // cnpj_teste = "34.183.784/000%-52";      // CNPJ Inv�lido
        // cnpj_teste = "34.183.784/0001-50";      // Digto Inv�lido
        // cnpj_teste = "34.183.784/0001-42";      // Digto Inv�lido
        // cnpj_teste = "7U.R9E.BBF/CTAU-77";      // Correto
        // cnpj_teste = "7U.R9E.BBF/CTAU-70";      // Digito Inv�lido
        // cnpj_teste = "7U.R9E.BBF/0000-77";      // Digito Inv�lido
        // cnpj_teste = "%%.R9E.BBF/CTAU-77";      // CNPJ Inv�lido
        // cnpj_teste = "7U.R@E.BBF/@TAU-77";      // CNPJ Inv�lido        

        
        // Teste de valida��o de CNPJ Alfanumerico
        cnpj_teste = "7U.R9E.BBF/CTAU-77";
        try {
            boolean valido = cnpjAlfa.ehValido(cnpj_teste);
            String status = valido ? "VALIDO" : "INV�LIDO";
            System.out.println("Teste de Valida��o -> CNPJ: " + cnpj_teste + " - " + status + "!");
        } catch (IllegalArgumentException e) {
            System.out.println("Teste de Valida��o -> Erro: " + e.getMessage());
        }
       
        // Teste de valida��o de CNPJ Alfanumerico
        cnpj_teste = "62.612.161/4838-01";
        try {
            boolean valido = cnpjAlfa.ehValido(cnpj_teste);
            String status = valido ? "VALIDO" : "INV�LIDO";
            System.out.println("Teste de Valida��o -> CNPJ: " + cnpj_teste + " - " + status + "!");
        } catch (IllegalArgumentException e) {
            System.out.println("Teste de Valida��o -> Erro: " + e.getMessage());
        }
       
		// Teste de gera��o de CNPJ Alfanumerico
		try {
		    String cnpjGerado = cnpjAlfa.geraCnpj(cnpj.ConstantesTipoCnpj.ALFANUMERICO);
		    System.out.println("Teste de Gera��o de CNPJ Alfanumerico -> CNPJ: " + cnpjGerado + " - Gerado Com Sucesso!");
		} catch (IllegalArgumentException e) {
		    System.out.println("Teste de Gera��o de CNPJ Alfanumerico -> Erro: " + e.getMessage());
		}        

		// Teste de gera��o de CNPJ Numerico
		try {
		    String cnpjGerado = cnpjAlfa.geraCnpj(cnpj.ConstantesTipoCnpj.NUMERICO);
		    System.out.println("Teste de Gera��o de CNPJ Numerico -> CNPJ: " + cnpjGerado + " - Gerado Com Sucesso!");
		} catch (IllegalArgumentException e) {
		    System.out.println("Teste de Gera��o de CNPJ Nuemrico -> Erro: " + e.getMessage());
		}
		
    }
}
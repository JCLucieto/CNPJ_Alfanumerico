package cnpj;

import java.util.Random;

public class CnpjAlfa {

    public static final String ALFANUMERICO = "A";
    public static final String NUMERICO = "N";
    
    // Fun��o: eh_valido
    // Objetivo: Validar um CNPJ Informado - Tamanho, Conte�do e Digito Verificador
    // Entrada: CNPJ Formatado (com a m�scara) ou N�o (somente n�meros)

    public boolean ehValido(String cnpj) throws IllegalArgumentException {
        // Verifica Caracteres
        if (!cnpj.matches("[0-9A-Za-z./-]+")) {
            throw new IllegalArgumentException("Caracteres Inv�lidos");
        }
        
        // Remove . / e - caso existam
        cnpj = cnpj.replaceAll("[./-]", "");

        // Verifica Tamanho
        if (cnpj.isEmpty()) {
            throw new IllegalArgumentException("CNPJ Vazio");
        }
        if (cnpj.length() < 14) {
            throw new IllegalArgumentException("Tamanho Inv�lido");
        }

        // Verifica Digito Verificador
        if (!verificaDV(cnpj)) {
            throw new IllegalArgumentException("Digito Inv�lido");
        }
        
        return true;
    }

    // Fun��o: gera_cnpj
    // Objetivo: Gerar um CNPJ aleat�rio do tipo Num�rico ou Alfanum�rico
    // Entrada: Tipo de CNPJ desejado (A ou N)
    // Sa�da: Uma string formatada com um CNPJ do tipo solicitado

    public String geraCnpj(String tipo) throws IllegalArgumentException {
        try {
            tipo = tipo.toUpperCase();
            String caracteresPossiveis;

            if (tipo.equals(ALFANUMERICO)) {
                // Define os caracteres poss�veis: 0-9 e A-Z
                caracteresPossiveis = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            } else if (tipo.equals(NUMERICO)) {
                // Define os caracteres poss�veis: 0-9
                caracteresPossiveis = "0123456789";
            } else {
                throw new IllegalArgumentException("Tipo de CNPJ Inv�lido! Digite A ou N");
            }

            // Gera a string com 12 caracteres aleat�rios
            StringBuilder resultado = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 12; i++) {
                int index = random.nextInt(caracteresPossiveis.length());
                resultado.append(caracteresPossiveis.charAt(index));
            }

            // Calcula o DV do CNPJ gerado
            String cnpjGerado = resultado.toString() + calculaDV(resultado.toString());

            // Formata a string no padr�o "AA.AAA.AAA/AAAA-99"
            return String.format("%s.%s.%s/%s-%s",
                    cnpjGerado.substring(0, 2),
                    cnpjGerado.substring(2, 5),
                    cnpjGerado.substring(5, 8),
                    cnpjGerado.substring(8, 12),
                    cnpjGerado.substring(12));
        } catch (Exception erro) {
            throw new IllegalArgumentException(erro.getMessage());
        }
    }

    // Fun��es Auxiliares (Acesso Interno da Classe)

    // Verifica se o DV est� Correto
    private boolean verificaDV(String cnpj) {
        String dvCalculado = calculaDV(cnpj);
        String dvInformado = cnpj.substring(12, 14);
        return dvCalculado.equals(dvInformado);
    }

    // Calcula os Digitos Verificadores de um CNPJ
    private int calcUmDigito(int[] multiplicadores, int[] cnpjParcial) {
        int soma = 0;
        for (int i = 0; i < multiplicadores.length; i++) {
            soma += multiplicadores[i] * cnpjParcial[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    private String calculaDV(String cnpj) {
        // Retira o DV do cnpj
        String cnpjSemDv = cnpj.substring(0, 12);

        // Converte o CNPJ para um array de inteiros
        int[] cnpjInts = new int[cnpjSemDv.length()];
        for (int i = 0; i < cnpjSemDv.length(); i++) {
            cnpjInts[i] = cnpjSemDv.charAt(i) - '0';
        }

        // Multiplicadores para o primeiro d�gito verificador
        int[] multiplicadores1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Multiplicadores para o segundo d�gito verificador
        int[] multiplicadores2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        // Calculando o primeiro d�gito verificador
        int primeiroDigito = calcUmDigito(multiplicadores1, cnpjInts);

        // Adicionando o primeiro d�gito para calcular o segundo
        int[] cnpjIntsComPrimeiroDigito = new int[cnpjInts.length + 1];
        System.arraycopy(cnpjInts, 0, cnpjIntsComPrimeiroDigito, 0, cnpjInts.length);
        cnpjIntsComPrimeiroDigito[cnpjInts.length] = primeiroDigito;

        // Calculando o segundo d�gito verificador
        int segundoDigito = calcUmDigito(multiplicadores2, cnpjIntsComPrimeiroDigito);

        return String.valueOf(primeiroDigito) + segundoDigito;
    }
}
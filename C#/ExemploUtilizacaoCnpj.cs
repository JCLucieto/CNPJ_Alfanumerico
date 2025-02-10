using System;

namespace Cnpj
{
    public class ExemploUtilizacaoCnpj
    {
        public static void Main(string[] args)
        {
            CnpjAlfa cnpjAlfa = new CnpjAlfa();

            // CNPJs Para Testes
            string cnpjTeste;

            // Teste de validação de CNPJ Alfanumerico
            cnpjTeste = "7U.R9E.BBF/CTAU-77";
            try
            {
                bool valido = cnpjAlfa.EhValido(cnpjTeste);
                string status = valido ? "VALIDO" : "INVÁLIDO";
                Console.WriteLine($"Teste de Validação -> CNPJ: {cnpjTeste} - {status}!");
            }
            catch (ArgumentException e)
            {
                Console.WriteLine($"Teste de Validação -> Erro: {e.Message}");
            }

            // Teste de validação de CNPJ Alfanumerico
            cnpjTeste = "62.612.161/4838-01";
            try
            {
                bool valido = cnpjAlfa.EhValido(cnpjTeste);
                string status = valido ? "VALIDO" : "INVÁLIDO";
                Console.WriteLine($"Teste de Validação -> CNPJ: {cnpjTeste} - {status}!");
            }
            catch (ArgumentException e)
            {
                Console.WriteLine($"Teste de Validação -> Erro: {e.Message}");
            }

            // Teste de geração de CNPJ Alfanumerico
            try
            {
                string cnpjGerado = cnpjAlfa.GeraCnpj(ConstantesTipoCnpj.ALFANUMERICO);
                Console.WriteLine($"Teste de Geração de CNPJ Alfanumerico -> CNPJ: {cnpjGerado} - Gerado Com Sucesso!");
            }
            catch (ArgumentException e)
            {
                Console.WriteLine($"Teste de Geração de CNPJ Alfanumerico -> Erro: {e.Message}");
            }

            // Teste de geração de CNPJ Numerico
            try
            {
                string cnpjGerado = cnpjAlfa.GeraCnpj(ConstantesTipoCnpj.NUMERICO);
                Console.WriteLine($"Teste de Geração de CNPJ Numerico -> CNPJ: {cnpjGerado} - Gerado Com Sucesso!");
            }
            catch (ArgumentException e)
            {
                Console.WriteLine($"Teste de Geração de CNPJ Numerico -> Erro: {e.Message}");
            }
        }
    }
}

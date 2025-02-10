using System;
using System.Text;
using System.Text.RegularExpressions;

namespace Cnpj
{
    public class CnpjAlfa
    {
        // Função: eh_valido
        // Objetivo: Validar um CNPJ Informado - Tamanho, Conteúdo e Digito Verificador
        // Entrada: CNPJ Formatado (com a máscara) ou Não (somente números)

        public bool EhValido(string cnpj)
        {
            // Verifica Caracteres
            if (!Regex.IsMatch(cnpj, @"[0-9A-Za-z./-]+"))
            {
                throw new ArgumentException("Caracteres Inválidos");
            }

            // Remove . / e - caso existam
            cnpj = cnpj.Replace(".", "").Replace("/", "").Replace("-", "");

            // Verifica Tamanho
            if (string.IsNullOrEmpty(cnpj))
            {
                throw new ArgumentException("CNPJ Vazio");
            }
            if (cnpj.Length < 14)
            {
                throw new ArgumentException("Tamanho Inválido");
            }

            // Verifica Digito Verificador
            if (!VerificaDV(cnpj))
            {
                throw new ArgumentException("Digito Inválido");
            }

            return true;
        }

        // Função: gera_cnpj
        // Objetivo: Gerar um CNPJ aleatório do tipo Numérico ou Alfanumérico
        // Entrada: Tipo de CNPJ desejado (A ou N)
        // Saída: Uma string formatada com um CNPJ do tipo solicitado

        public string GeraCnpj(string tipo)
        {
            try
            {
                tipo = tipo.ToUpper();
                string caracteresPossiveis;

                if (tipo.Equals(ConstantesTipoCnpj.ALFANUMERICO))
                {
                    // Define os caracteres possíveis: 0-9 e A-Z
                    caracteresPossiveis = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                }
                else if (tipo.Equals(ConstantesTipoCnpj.NUMERICO))
                {
                    // Define os caracteres possíveis: 0-9
                    caracteresPossiveis = "0123456789";
                }
                else
                {
                    throw new ArgumentException("Tipo de CNPJ Inválido! Digite A ou N");
                }

                // Gera a string com 12 caracteres aleatórios
                var resultado = new StringBuilder();
                var random = new Random();
                for (int i = 0; i < 12; i++)
                {
                    int index = random.Next(caracteresPossiveis.Length);
                    resultado.Append(caracteresPossiveis[index]);
                }

                // Calcula o DV do CNPJ gerado
                string cnpjGerado = resultado.ToString() + CalculaDV(resultado.ToString());

                // Formata a string no padrão "AA.AAA.AAA/AAAA-99"
                return string.Format("{0}.{1}.{2}/{3}-{4}",
                    cnpjGerado.Substring(0, 2),
                    cnpjGerado.Substring(2, 3),
                    cnpjGerado.Substring(5, 3),
                    cnpjGerado.Substring(8, 4),
                    cnpjGerado.Substring(12));
            }
            catch (Exception erro)
            {
                throw new ArgumentException(erro.Message);
            }
        }

        // Funções Auxiliares (Acesso Interno da Classe)

        // Verifica se o DV está Correto
        private bool VerificaDV(string cnpj)
        {
            string dvCalculado = CalculaDV(cnpj);
            string dvInformado = cnpj.Substring(12, 2);
            return dvCalculado.Equals(dvInformado);
        }

        // Calcula os Digitos Verificadores de um CNPJ
        private int CalcUmDigito(int[] multiplicadores, int[] cnpjParcial)
        {
            int soma = 0;
            for (int i = 0; i < multiplicadores.Length; i++)
            {
                soma += multiplicadores[i] * cnpjParcial[i];
            }
            int resto = soma % 11;
            return (resto < 2) ? 0 : 11 - resto;
        }

        private string CalculaDV(string cnpj)
        {
            // Retira o DV do cnpj
            string cnpjSemDv = cnpj.Substring(0, 12);

            // Converte o CNPJ para um array de inteiros
            var cnpjInts = new int[cnpjSemDv.Length];
            for (int i = 0; i < cnpjSemDv.Length; i++)
            {
                cnpjInts[i] = cnpjSemDv[i] - '0';
            }

            // Multiplicadores para o primeiro dígito verificador
            int[] multiplicadores1 = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

            // Multiplicadores para o segundo dígito verificador
            int[] multiplicadores2 = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

            // Calculando o primeiro dígito verificador
            int primeiroDigito = CalcUmDigito(multiplicadores1, cnpjInts);

            // Adicionando o primeiro dígito para calcular o segundo
            var cnpjIntsComPrimeiroDigito = new int[cnpjInts.Length + 1];
            Array.Copy(cnpjInts, 0, cnpjIntsComPrimeiroDigito, 0, cnpjInts.Length);
            cnpjIntsComPrimeiroDigito[cnpjInts.Length] = primeiroDigito;

            // Calculando o segundo dígito verificador
            int segundoDigito = CalcUmDigito(multiplicadores2, cnpjIntsComPrimeiroDigito);

            return primeiroDigito.ToString() + segundoDigito.ToString();
        }
    }
}

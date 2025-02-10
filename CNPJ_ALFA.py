#-------------------------------------------------------------
# Classe CNPJ_ALFA - Trata CNPJ Numérico e Nova Versão ALFA
#-------------------------------------------------------------

# Imports

import re
import random
import string

# Definição da Classe

class CNPJ_ALFA:

    # Função   : eh_valido
    # Objetivo : Validar um CNPJ Informado - Tamanho, Conteúdo e Digito Verificar
    # Entrada  : CNPJ Formatado (com a mascara) ou Não (somente números)
    
    def eh_valido (self, cnpj):

        # Verifica Caracteres
        if re.search(r'[^0-9A-Za-z./-]', cnpj):
            raise ValueError('Caracteres Inválidos')
            return False
        # Remove . / e - caso existam
        cnpj = cnpj.replace('.', '').replace('/', '').replace('-', '')
        # Verifica Tamanho
        if not cnpj:
            raise ValueError('CNPJ Vazio')
            return False
        if len(cnpj) < 14:
            raise ValueError('Tamanho Inválido')
            return False
        # Verifica Digito Verificador
        if not self.verifica_dv(cnpj):
            raise ValueError('Digito Inválido')
            return False  
        return True

    # Função   : gera_cnpj
    # Objetivo : Gerar um CNPJ aleatório do tipo Numérico ou Alfanumerico
    # Entrada  : Tipo de CNPJ desejado (A ou N)
    # Saída    : Uma string formatada com um CNPJ do tipo solicitado
    
    def gera_cnpj (self, tipo):

        try:
            # Consiste Tipo
            tipo = tipo.upper()
            if tipo == 'A':
                # Define os caracteres possíveis: 0-9 e A-Z
                caracteres_possiveis = string.digits + string.ascii_uppercase
            elif tipo == 'N':
                # Define os caracteres possíveis: 0-9
                caracteres_possiveis = string.digits
            else:
                raise ValueError('Tipo de CNPJ Inválido! Digite A ou N')
            # Gera a string com 12 caracteres aleatórios
            resultado = ''.join(random.choice(caracteres_possiveis) for _ in range(12))
            # Calcula o DV do CNPJ gerado
            cnpf_gerado = resultado + self.calcula_dv(resultado)
            # Formata a string no padrão "AA.AAA.AAA/AAAA-99"
            cnpj_formatado = f"{cnpf_gerado[:2]}.{cnpf_gerado[2:5]}.{cnpf_gerado[5:8]}/{cnpf_gerado[8:12]}-{cnpf_gerado[12:]}"
            return cnpj_formatado        
        except Exception as erro:
            raise ValueError(erro)
            return False

    # Funções Auxiliares (Acesso Interno da Classe)

    # Verifica se o DV está Correto

    def verifica_dv(self, cnpj):
        dv_calculado = self.calcula_dv(cnpj)
        dv_informado = cnpj[12:14]
        if (dv_calculado != dv_informado):
            return False
        else:
            return True
    
    # Calcula os Digitos Verificadores de um CNPJ

    def calc_um_digito(self, multiplicadores, cnpj_parcial):

        soma = sum(m * n for m, n in zip(multiplicadores, cnpj_parcial))
        resto = soma % 11
        return 0 if resto < 2 else 11 - resto
        
    def calcula_dv(self, cnpj):

        # Retira o DV do cnpj
        cnpj_sem_dv = cnpj[:12]
        # List comprehension para converter cada caractere em seu valor ASCII
        ascii_values = [ord(char) for char in cnpj_sem_dv]
        # Converte em uma lista de inteiros subtraindo 48
        cnpj_ints = [(int(digito) - 48) for digito in ascii_values]
        # Multiplicadores para o primeiro dígito verificador
        multiplicadores_1 = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
        # Multiplicadores para o segundo dígito verificador
        multiplicadores_2 = [6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
        # Calculando o primeiro dígito verificador
        primeiro_digito = self.calc_um_digito(multiplicadores_1, cnpj_ints)
        # Adicionando o primeiro dígito para calcular o segundo
        cnpj_ints.append(primeiro_digito)
        # Calculando o segundo dígito verificador
        segundo_digito = self.calc_um_digito(multiplicadores_2, cnpj_ints)
        dvs_calculados = str(primeiro_digito) + str(segundo_digito)
        return dvs_calculados   

#---------------------------------------------------------
# Exemplo de Utilização da Classe CNPJ_ALFA
#---------------------------------------------------------

from CNPJ_ALFA import CNPJ_ALFA

def main():
   
    # Criação de uma instância de CNPJ_ALFA
    meu_cnpj = CNPJ_ALFA()

    # CNPJs Para Testes

    cnpj_teste = '62.612.161/4838-01'        # Correto
    # cnpj_teste = '34.183.784/0001-52'      # Correto
    # cnpj_teste = '34.183.784/000%-52'      # CNPJ Inválido
    # cnpj_teste = '34.183.784/0001-50'      # Digto Inválido
    # cnpj_teste = '34.183.784/0001-42'      # Digto Inválido
    # cnpj_teste = '7U.R9E.BBF/CTAU-77'      # Correto
    # cnpj_teste = '7U.R9E.BBF/CTAU-70'      # Digito Inválido
    # cnpj_teste = '7U.R9E.BBF/0000-77'      # Digito Inválido
    # cnpj_teste = '%%.R9E.BBF/CTAU-77'      # CNPJ Inválido
    # cnpj_teste = '7U.R@E.BBF/@TAU-77'      # CNPJ Inválido

    #------------------------------------------------------------
    # Chamada do metodo de validação de um CNPJ
    #------------------------------------------------------------

    try:
        if (meu_cnpj.eh_valido (cnpj_teste)):
            print (f'O CNPJ: {cnpj_teste} é Valido!\n')
    except ValueError as e:
        print(f'Erro na Validação do CNPJ: {cnpj_teste} - {e}\n')


    #------------------------------------------------------------
    # Chamada do metodo que gera um CNPJ Alfa ou Numérico
    #------------------------------------------------------------

    tipo_alfanumerico = 'A'
    tipo_numerico     = 'N'
    
    try:
        cnpj_gerado = meu_cnpj.gera_cnpj(tipo_alfanumerico)
        print (f'Geração com Sucesso do CNPJ : {cnpj_gerado}\n')
    except ValueError as e:
        print(f'Erro na Geração do CNPJ : {e}\n')

#------------------------------------------------------------
# Código Principal de Ativação
#------------------------------------------------------------

if __name__ == '__main__':
    main()

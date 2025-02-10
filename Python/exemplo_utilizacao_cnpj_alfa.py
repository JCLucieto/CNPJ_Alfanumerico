#---------------------------------------------------------
# Exemplo de Utilização da Classe CNPJ_ALFA
#---------------------------------------------------------

import constantes_tipo_cnpj as constp
from cnpj_alfa import Cnpj_Alfa

def main():
   
    # Criação de uma instância de CNPJ_ALFA
    meu_cnpj = Cnpj_Alfa()

    # CNPJs Para Testes

    # cnpj_teste = '62.612.161/4838-01'      # Correto
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
    # Chamada do metodo de validação de um CNPJ Numérico
    #------------------------------------------------------------

    cnpj_teste = '62.612.161/4838-01' # Valido
    try:
        if (meu_cnpj.eh_valido (cnpj_teste)):
            print (f'O CNPJ: {cnpj_teste} é Valido!\n')
    except ValueError as e:
        print(f'Erro na Validação do CNPJ: {cnpj_teste} - {e}\n')

    #------------------------------------------------------------
    # Chamada do metodo de validação de um CNPJ AlfaNumérico
    #------------------------------------------------------------

    cnpj_teste = '7U.R9E.BBF/CTAU-77' # Valido
    try:
        if (meu_cnpj.eh_valido (cnpj_teste)):
            print (f'O CNPJ: {cnpj_teste} é Valido!\n')
    except ValueError as e:
        print(f'Erro na Validação do CNPJ: {cnpj_teste} - {e}\n')

    #------------------------------------------------------------
    # Chamada do metodo que gera um CNPJ Numérico
    #------------------------------------------------------------
    
    try:
        cnpj_gerado = meu_cnpj.gera_cnpj(constp.NUMERICO)
        print (f'Geração com Sucesso do CNPJ : {cnpj_gerado}\n')
    except ValueError as e:
        print(f'Erro na Geração do CNPJ : {e}\n')


    #------------------------------------------------------------
    # Chamada do metodo que gera um CNPJ Alfanumerico
    #------------------------------------------------------------
    
    try:
        cnpj_gerado = meu_cnpj.gera_cnpj(constp.ALFANUMERICO)
        print (f'Geração com Sucesso do CNPJ : {cnpj_gerado}\n')
    except ValueError as e:
        print(f'Erro na Geração do CNPJ : {e}\n')        


#------------------------------------------------------------
# Código Principal de Ativação
#------------------------------------------------------------

if __name__ == '__main__':
    main()

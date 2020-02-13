import sys
print(f"This is the name of the script: {sys.argv[0]}")
print(f"Number of arguments: {len(sys.argv)} ")
print(f"The arguments are: {str(sys.argv)} ")

if len(sys.argv) > 2:
    print('aqui')
    print(sys.argv[2])

table_name = 'srmto.sensoriamento_mobile_sessao'

if len(sys.argv) > 1:
    table_name = sys.argv[1]

print(table_name)
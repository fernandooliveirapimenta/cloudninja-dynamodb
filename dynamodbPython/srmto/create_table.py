import boto3
import sys
dynamodb = boto3.client('dynamodb')
table_name = 'srmto.sensoriamento_mobile_sessao'
if len(sys.argv) > 1:
    table_name = sys.argv[1]
try:
    dynamodb.create_table(
        TableName=table_name,
        AttributeDefinitions=[
            {
                "AttributeName": "hash",
                "AttributeType": "S"
            },
            {
                "AttributeName": "range",
                "AttributeType": "S"
            }
        ],
        KeySchema=[
            {
                "AttributeName": "hash",
                "KeyType": "HASH"
            },
            {
                "AttributeName": "range",
                "KeyType": "RANGE"
            }
        ],
        GlobalSecondaryIndexes=[
            {
                "IndexName": "InvertedIndex",
                "KeySchema": [
                    {
                        "AttributeName": "range",
                        "KeyType": "HASH"
                    },
                    {
                        "AttributeName": "hash",
                        "KeyType": "RANGE"
                    }
                ],
                "Projection": {
                    "ProjectionType": "ALL"
                }
            }
        ],
        BillingMode='PAY_PER_REQUEST',
    )
    print(f"Tabela {table_name} criada com sucesso!!")
except Exception as e:
    print(f"Erro ao tentar criar tabela {table_name}")
    print(e)

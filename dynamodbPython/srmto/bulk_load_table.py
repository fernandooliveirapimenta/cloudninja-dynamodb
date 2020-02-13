import json
import sys
import boto3

table_name = 'srmto.sensoriamento_mobile_sessao'
if len(sys.argv) > 1:
    table_name = sys.argv[1]

dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table(table_name)

items = []

with open('items.json', 'r') as f:
    for row in f:
        items.append(json.loads(row))

with table.batch_writer() as batch:
    for item in items:
        batch.put_item(Item=item)

package com.example.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.example.dynamodb.model.SensoriamentoSessao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TesteSrmtoPodcasts {

    public static void main(String[] args) {
        DynamoDBConfig c = new DynamoDBConfig();

        final DynamoDBMapper dynamoDBMapper = c.dynamoDBMapper();

        DynamoDBScanExpression se = new DynamoDBScanExpression();
        final PaginatedScanList<SensoriamentoSessao> scan = dynamoDBMapper.scan(SensoriamentoSessao.class, se);

        scan.forEach(System.out::println);
//        final SensoriamentoSessao load = dynamoDBMapper.load(SensoriamentoSessao.class, "USER#maryharris");
//        System.out.println(load);

        query(dynamoDBMapper);
    }

    /**
     * rangeAttributeName = :rangeval - true if the range key is equal to :rangeval.
     *
     * rangeAttributeName < :rangeval - true if the range key is less than :rangeval.
     *
     * rangeAttributeName <= :rangeval - true if the range key is less than or equal to :rangeval.
     *
     * rangeAttributeName > :rangeval - true if the range key is greater than :rangeval.
     *
     * rangeAttributeName >= :rangeval - true if the range key is greater than or equal to :rangeval.
     *
     * rangeAttributeName BETWEEN :rangeval1 AND :rangeval2 - true if the range
     * key is greater than or equal to :rangeval1, and less than or equal to :rangeval2.
     *
     * begins_with (rangeAttributeName, :rangeval) - true if the range key begins with a particular operand.
     * Note that the function name begins_with is case-sensitive.
     */
    private static void query(DynamoDBMapper dynamoDBMapper) {
        Map<String,String> expressionAttributesNames = new HashMap<>();
        expressionAttributesNames.put("#id","hash");
        expressionAttributesNames.put("#sort","range");

        Map<String, AttributeValue> expressionAttributeValues = new HashMap<>();
        expressionAttributeValues.put(":idVal",new AttributeValue().withS("USER#lindsay56"));
        expressionAttributeValues.put(":sortVal",new AttributeValue().withS("#MET"));

        DynamoDBQueryExpression<SensoriamentoSessao> queryExpression =
                new DynamoDBQueryExpression<SensoriamentoSessao>()
                        .withKeyConditionExpression("#id = :idVal and begins_with(#sort, :sortVal)")
                .withExpressionAttributeNames(expressionAttributesNames)
                .withExpressionAttributeValues(expressionAttributeValues);

        final PaginatedQueryList<SensoriamentoSessao> query =
                dynamoDBMapper.query(SensoriamentoSessao.class, queryExpression);
        query.forEach(System.out::println);

        save(dynamoDBMapper);
    }

    private static void save(DynamoDBMapper mapper){
        SensoriamentoSessao s = new SensoriamentoSessao();
        s.setHash("PODCASTS#"+ UUID.randomUUID().toString());
        s.setRange("#METADATA#43743772809");
        mapper.save(s);

        mapper.save(s);

    }
}

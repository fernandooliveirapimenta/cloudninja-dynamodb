package com.example.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;


@DynamoDBTable(tableName="fernando")
public class SensoriamentoSessao {

 @DynamoDBHashKey
 private String hash;
 @DynamoDBRangeKey
 private String range;

 public String getHash() {
  return hash;
 }

 public void setHash(String hash) {
  this.hash = hash;
 }

 public String getRange() {
  return range;
 }

 public void setRange(String range) {
  this.range = range;
 }

 @Override
 public String toString() {
  return "SensoriamentoSessao{" +
          "hash='" + hash + '\'' +
          ", range='" + range + '\'' +
          '}';
 }
}
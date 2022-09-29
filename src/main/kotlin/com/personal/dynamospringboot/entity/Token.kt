package com.personal.dynamospringboot.entity

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable

@DynamoDBTable(tableName = "token")
class Token {

    @DynamoDBHashKey
    lateinit var hashedData: String

    @DynamoDBAttribute
    lateinit var token: String
}
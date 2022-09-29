package com.personal.dynamospringboot.repo

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.personal.dynamospringboot.entity.Token
import org.springframework.stereotype.Repository

@Repository
class TokenRepoImpl(private val dynamoDBMapper: DynamoDBMapper) : TokenRepo{

    override fun save(token: Token) {
       return dynamoDBMapper.save(token)
    }

    override fun getByHashedData(hashedData: String): Token? {
        return dynamoDBMapper.load(Token::class.java, hashedData)
    }
}
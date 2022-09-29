package com.personal.dynamospringboot.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DynamoDbConfig(private val dynamoDBProperties: DynamoDBProperties) {

    @Bean
    fun dynamoDBMapper(): DynamoDBMapper {
        return DynamoDBMapper(buildAmazonDynamoDB())
    }

    fun buildAmazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(dynamoDBProperties.serviceEndpoint, dynamoDBProperties.region)
            )
            .withCredentials(
                AWSStaticCredentialsProvider(BasicAWSCredentials(dynamoDBProperties.accessKey, dynamoDBProperties.secretKey))
            )
            .build()
    }
}
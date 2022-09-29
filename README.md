# dynamo-db-spring-boot

Integration of AWS DyanamoDB with Spring Boot and Kotlin

#### Configuration:

```Kotlin
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



```

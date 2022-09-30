# dynamo-db-spring-boot

Integration of AWS DyanamoDB with Spring Boot and Kotlin


#### Dependency:

```xml
<dependency>
   <groupId>com.amazonaws</groupId>
   <artifactId>aws-java-sdk-dynamodb</artifactId>
   <version>1.12.312</version>
</dependency>

```

#### Configuration:

```Kotlin
@Configuration
class DynamoDbConfig(private val properties: DynamoDBProperties) {

    @Bean
    fun dynamoDBMapper(): DynamoDBMapper {
        return DynamoDBMapper(buildAmazonDynamoDB())
    }

    fun buildAmazonDynamoDB(): AmazonDynamoDB {
        return AmazonDynamoDBClientBuilder.standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(properties.serviceEndpoint, properties.region)
            )
            .withCredentials(
                AWSStaticCredentialsProvider(BasicAWSCredentials(properties.accessKey, properties.secretKey))
            )
            .build()
    }
}

```

#### Table

```Kotlin
@DynamoDBTable(tableName = "token")
class Token {

    @DynamoDBHashKey
    lateinit var hashedData: String

    @DynamoDBAttribute
    lateinit var token: String
}
```

#### Repository

```Kotlin
@Repository
class TokenRepoImpl(private val dynamoDBMapper: DynamoDBMapper) : TokenRepo{

    override fun save(token: Token) {
       return dynamoDBMapper.save(token)
    }

    override fun getByHashedData(hashedData: String): Token? {
        return dynamoDBMapper.load(Token::class.java, hashedData)
    }
}
```

Notes:

- [Reactive Integration of DynamoDB with Spring Webflux and Kotlin](https://github.com/BimalRajGyawali/dynamo-db-reactive-spring)
- `com.amazonaws.aws-java-sdk-dynamodb` is v1 sdk 
- `software.amazon.awssdk.dynamodb` and `software.amazon.awssdk.dynamodb-enhanced` are v2 sdk. Enhanced has async capabilities.

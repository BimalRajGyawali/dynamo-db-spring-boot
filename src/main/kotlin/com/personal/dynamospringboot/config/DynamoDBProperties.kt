package com.personal.dynamospringboot.config

import org.springframework.stereotype.Component

@Component
class DynamoDBProperties {

    val serviceEndpoint = "http://localhost:8000"
    val region = "ap-south-1"
    val accessKey = "local"
    val secretKey = "local"

}
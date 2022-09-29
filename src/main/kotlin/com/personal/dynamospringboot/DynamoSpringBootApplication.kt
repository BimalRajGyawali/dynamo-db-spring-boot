package com.personal.dynamospringboot

import com.personal.dynamospringboot.entity.Token
import com.personal.dynamospringboot.repo.TokenRepo
import org.slf4j.LoggerFactory
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DynamoSpringBootApplication(private val tokenRepo: TokenRepo){

	private val logger = LoggerFactory.getLogger(DynamoSpringBootApplication::class.java)

	@Bean
	fun applicationRunner(): ApplicationRunner{
		return ApplicationRunner {
			val tokenToBeSaved = Token()
			tokenToBeSaved.hashedData = "123"
			tokenToBeSaved.token = "Kotlin"

			tokenRepo.save(tokenToBeSaved)

			val savedToken = tokenRepo.getByHashedData("123")
			logger.info("Saved token {}", savedToken?.token)

		}
	}
}

fun main(args: Array<String>) {
	runApplication<DynamoSpringBootApplication>(*args)
}

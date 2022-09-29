package com.personal.dynamospringboot.repo

import com.personal.dynamospringboot.entity.Token

interface TokenRepo  {

    fun save(token: Token)

    fun getByHashedData(hashedData: String): Token?
}
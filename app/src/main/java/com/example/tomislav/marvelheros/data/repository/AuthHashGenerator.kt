package com.example.tomislav.marvelheros.data.repository

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.experimental.or

internal class AuthHashGenerator {
    @Throws(MarvelApiException::class)
    fun generateHash(timestamp: String, publicKey: String, privateKey: String): String? {
        try {
            val md = MessageDigest.getInstance("MD5")
            val toHash = timestamp + privateKey + publicKey
            return BigInteger(1, md.digest(toHash.toByteArray())).toString(16)
        } catch (e: NoSuchAlgorithmException) {
            throw MarvelApiException("Cannot generate api string",e)
        }

        return null

    }


}


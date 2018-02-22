package com.example.tomislav.marvelheros.data.repository

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.experimental.or

internal class AuthHashGenerator {
    @Throws(MarvelApiException::class)
    fun generateHash(timestamp: String, publicKey: String, privateKey: String): String {
        try {
            val value = timestamp + privateKey + publicKey
            val md5Encoder = MessageDigest.getInstance("MD5")
            val md5Bytes = md5Encoder.digest(value.toByteArray())
            val md5 = StringBuilder()

            md5Bytes.forEach  {
                md5.append(Integer.toHexString(((it and 0xFF.toByte()) or 0x100.toByte()).toInt()).substring(1, 3))
            }
            return md5.toString()
        } catch (e: NoSuchAlgorithmException) {
            throw MarvelApiException("Cannot generate the api key", e)
        }

    }
}


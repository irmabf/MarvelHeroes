package com.example.tomislav.marvelheros.data.repository

import android.util.Log
import java.io.IOException

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

internal class AuthInterceptor(private val publicKey: String, private val privateKey: String) : Interceptor {
    private val authHashGenerator = AuthHashGenerator()

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val timestamp = System.currentTimeMillis().toString()
        var hash: String? = null
        try {
            hash = authHashGenerator.generateHash(timestamp, publicKey, privateKey)
        } catch (e: MarvelApiException) {
            e.printStackTrace()
        }

        var request = chain.request()
        val url = request.url()
                .newBuilder()
                .addQueryParameter(TIMESTAMP_KEY, timestamp)
                .addQueryParameter(APIKEY_KEY, publicKey)
                .addQueryParameter(HASH_KEY, hash)
                .build()
        request = request.newBuilder().url(url).build()
        Log.d("Marvel URL string: ", request.url().encodedPath())
        return chain.proceed(request)
    }

    companion object {
        private val TIMESTAMP_KEY = "ts"
        private val HASH_KEY = "hash"
        private val APIKEY_KEY = "apikey"
    }
}




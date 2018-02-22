package com.example.tomislav.marvelheros.data.repository

class MarvelApiException(val httpCode: Int, val marvelCode: String, description: String, cause: Throwable) : Exception(description, cause) {

    constructor(message: String, cause: Throwable) : this(-1, "", message, cause) {}
}

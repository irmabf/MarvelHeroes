package com.example.tomislav.marvelheros.data.repository

import retrofit2.http.GET
import java.util.*

interface MarvelService{
    companion object {
        val HTTPS_API_MARVEL_URL = "https://gateway.marvel.com/"
    }

    @GET("/v1/public/characters")
    fun getHeroesList():Observable<List<Hero>>



}
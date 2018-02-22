package com.example.tomislav.marvelheros.data.repository

import com.example.tomislav.marvelheros.data.model.Model
import io.reactivex.Observable
import retrofit2.http.GET

interface MarvelService{
    companion object {
        val HTTPS_API_MARVEL_URL = "https://gateway.marvel.com/"
    }

    @GET("/v1/public/characters")
    fun getHeroesList(): Observable<Model.MarvelResponse>



}
package com.example.tomislav.marvelheros.data.repository

import com.example.tomislav.marvelheros.data.model.Models
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService{

    @GET("/v1/public/characters")
    fun getHeroesList(@Query("offset") offset: Int,
                      @Query("limit") limit: Int): Call<Models.MarvelResponse>

}
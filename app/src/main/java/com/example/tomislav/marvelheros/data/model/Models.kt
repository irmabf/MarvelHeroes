package com.example.tomislav.marvelheros.data.model

object Model {
    data class MarvelResponse(val data: Results)
    data class Results(val results: List<Hero>)
    data class Hero(val id:Int, val name:String, val urls:List<MarvelHeroURL> )
    data class MarvelHeroURL(val type:String, val url:String)
}

package com.example.tomislav.marvelheros.data.model

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList

object Models {
    data class MarvelResponse(val data: Results)
    data class Results(val offset:Int, val limit:Int, val total:Int, val count:Int, val results: List<Hero>)
    data class Hero(val id:Int, val name:String, val urls:List<MarvelHeroURL>, val thumbnail:Image)
    data class Image(val path:String)
    data class MarvelHeroURL(val type:String, val url:String)

}

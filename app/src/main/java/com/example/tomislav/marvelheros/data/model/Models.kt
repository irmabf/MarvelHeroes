package com.example.tomislav.marvelheros.data.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

object Models {
    data class MarvelResponse(val data: Results)
    data class Results(val offset:Int, val limit:Int, val total:Int, val count:Int, val results: List<Hero>)
    @Parcelize
    data class Image(val path:String,val extension:String):Parcelable{
        fun getImageUrl()=StringBuilder(this.path).append(".").append(this.extension).toString()
    }
    @Parcelize
    data class MarvelHeroURL(val type:String, val url:String):Parcelable
    @Parcelize
    data class Hero(val id:Int, val name:String, val urls:List<MarvelHeroURL>, val thumbnail:Image):Parcelable
}

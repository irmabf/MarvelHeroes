package com.example.tomislav.marvelheros.Utils


import android.content.Context
import android.net.ConnectivityManager
import io.reactivex.Observable


object NetworkUtils {

    private fun isNetworkAvailable(context: Context):Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


    fun isNetworkAvailableObservable(context: Context) = Observable.just(NetworkUtils.isNetworkAvailable(context))

}
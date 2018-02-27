package com.example.tomislav.marvelheros.data.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PositionalDataSource
import com.example.tomislav.marvelheros.data.model.Models
import com.example.tomislav.marvelheros.data.model.NetworkState
import retrofit2.Call
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.Executor

class HeroesDataSource(
        private val marvelService: MarvelService,
        private val retryExecutor: Executor) : PositionalDataSource<Models.Hero>() {


    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Models.Hero>) {
        networkState.postValue(NetworkState.LOADING)
        marvelService.getHeroesList(
                offset = params.startPosition,
                limit = params.loadSize).enqueue(
                object : retrofit2.Callback<Models.MarvelResponse> {
                    override fun onFailure(call: Call<Models.MarvelResponse>, t: Throwable) {
                        retry = {
                            loadRange(params, callback)
                        }
                        networkState.postValue(NetworkState.error(t.message
                                ?: "unknown err"))
                    }

                    override fun onResponse(
                            call: Call<Models.MarvelResponse>,
                            response: Response<Models.MarvelResponse>) {
                        if (response.isSuccessful) {
                            val data = response.body()?.data
                            val items = data?.results?.map { it } ?: emptyList()
                            retry = null
                            callback.onResult(items)
                            networkState.postValue(NetworkState.LOADED)
                        } else {
                            retry = {
                                loadRange(params, callback)
                            }
                            networkState.postValue(
                                    NetworkState.error("error code: ${response.code()}"))
                        }
                    }
                }
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Models.Hero>) {
        val request = marvelService.getHeroesList(
                limit = params.requestedLoadSize,
                offset = params.requestedStartPosition
        )
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        // triggered by a refresh, we better execute sync
        try {
            val response = request.execute()
            val data = response.body()?.data
            val items = data?.results?.map { it } ?: emptyList()
            retry = null
            networkState.postValue(NetworkState.LOADED)
            initialLoad.postValue(NetworkState.LOADED)
            callback.onResult(items, data?.offset!!)
        } catch (ioException: IOException) {
            retry = {
                loadInitial(params, callback)
            }
            val error = NetworkState.error(ioException.message
                    ?: "unknown error")
            networkState.postValue(error)
            initialLoad.postValue(error)
        }
    }

    // keep a function reference for the retry event
    private var retry: (() -> Any)? = null

    /**
     * There is no sync on the state because paging will always call loadInitial first then wait
     * for it to return some success value before calling loadAfter.
     */
    val networkState = MutableLiveData<NetworkState>()

    val initialLoad = MutableLiveData<NetworkState>()

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            retryExecutor.execute {
                it.invoke()
            }
        }
    }






}
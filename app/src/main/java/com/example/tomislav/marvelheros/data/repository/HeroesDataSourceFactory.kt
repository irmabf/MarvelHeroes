package com.example.tomislav.marvelheros.data.repository

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import com.example.tomislav.marvelheros.data.model.Models
import java.util.concurrent.Executor

class HeroesDataSourceFactory(
        private val marvelService: MarvelService,
        private val retryExecutor: Executor) : DataSource.Factory<String, Models.Hero> {
    val sourceLiveData = MutableLiveData<HeroesDataSource>()
    override fun create(): DataSource<String, Models.Hero> {
        val source = HeroesDataSource(marvelService, retryExecutor)
        sourceLiveData.postValue(source)
        return source as DataSource<String, Models.Hero>
    }
}
package com.example.tomislav.marvelheros.data.repository

import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.support.annotation.MainThread
import com.example.tomislav.marvelheros.data.model.Listing
import com.example.tomislav.marvelheros.data.model.Models
import java.util.concurrent.Executor
import javax.inject.Singleton


@Singleton
class HeroesByPageKeyRepository(private val marvelService: MarvelService,
                                  private val networkExecutor: Executor) {
    @MainThread
    fun heroesList(pageSize: Int): Listing<Models.Hero> {
        val sourceFactory = HeroesDataSourceFactory(marvelService, networkExecutor)

        val livePagedList = LivePagedListBuilder(sourceFactory, PagedList.Config.Builder().setPageSize(pageSize).setEnablePlaceholders(false).setPrefetchDistance(20).build())
                // provide custom executor for network requests, otherwise it will default to
                // Arch Components' IO pool which is also used for disk access
                .setBackgroundThreadExecutor(networkExecutor)
                .build()

        val refreshState = Transformations.switchMap(sourceFactory.sourceLiveData) {
            it.initialLoad
        }
        return Listing(
                pagedList = livePagedList,
                networkState = Transformations.switchMap(sourceFactory.sourceLiveData, {
                    it.networkState
                }),
                retry = {
                    sourceFactory.sourceLiveData.value?.retryAllFailed()
                },
                refresh = {
                    sourceFactory.sourceLiveData.value?.invalidate()
                },
                refreshState = refreshState
        )
    }
}
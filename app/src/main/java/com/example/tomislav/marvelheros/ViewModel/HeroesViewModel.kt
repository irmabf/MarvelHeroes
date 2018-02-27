package com.example.tomislav.marvelheros.ViewModel

import android.arch.lifecycle.ViewModel
import com.example.tomislav.marvelheros.data.repository.HeroesByPageKeyRepository
import javax.inject.Inject


class HeroesViewModel @Inject constructor(private val repository: HeroesByPageKeyRepository): ViewModel(){
    private val repoResult = repository.heroesList(10)

    val heroes = repoResult.pagedList
    val networkState = repoResult.networkState
    val refreshState = repoResult.refreshState

    fun refresh() {
        repoResult.refresh?.invoke()
    }

    fun retry() {
        repoResult?.refresh?.invoke()
    }
}
package com.example.tomislav.marvelheros.di

import com.example.tomislav.marvelheros.data.repository.HeroesByPageKeyRepository
import com.example.tomislav.marvelheros.data.repository.MarvelService
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideHeroesRepository(marvelService: MarvelService, networkExecutor: Executor) = HeroesByPageKeyRepository(marvelService, networkExecutor)

    @Singleton
    @Provides
    fun provideNetworkExecutor() = Executors.newFixedThreadPool(5)

}
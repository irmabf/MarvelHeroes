package com.example.tomislav.marvelheros.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
class AppModule{
    @Singleton
    @Provides
    fun provideMarvelService(): MarvelService{
    }

    @Singleton
    @Provides
    fun provideViewModelFactory():ViewModelProvider.Factory{

    }
}

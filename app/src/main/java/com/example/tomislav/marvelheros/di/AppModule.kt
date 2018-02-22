package com.example.tomislav.marvelheros.di

import android.arch.lifecycle.ViewModelProvider
import com.example.tomislav.marvelheros.ViewModel.HeroesViewModelFactory
import com.example.tomislav.marvelheros.data.repository.MarvelService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
class AppModule{
    companion object {
        val BASE_URL = "https://gateway.marvel.com/"
    }

    @Singleton
    @Provides
    internal fun provideApiService(client: OkHttpClient, moshi: MoshiConverterFactory): MarvelService? {
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(moshi)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(MarvelService::class.java)
    }

    @Singleton
    @Provides
    fun provideViewModelFactory(viewModelSubComponent: ViewModelSubComponent.Builder)=HeroesViewModelFactory(viewModelSubComponent.build())
}

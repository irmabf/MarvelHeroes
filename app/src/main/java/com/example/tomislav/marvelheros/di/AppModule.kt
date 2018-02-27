package com.example.tomislav.marvelheros.di

import com.example.tomislav.marvelheros.View.ui.HeroesFragment
import com.example.tomislav.marvelheros.View.ui.HeroesListFragment
import com.example.tomislav.marvelheros.data.repository.MarvelService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class AppModule{

    companion object {
        val BASE_URL = "https://gateway.marvel.com/"
    }

    @Singleton
    @Provides
    internal fun provideApiService(client: OkHttpClient, moshi: MoshiConverterFactory): MarvelService {
        val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(moshi)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return  retrofit.create(MarvelService::class.java)
    }

    @Singleton
    @Provides
    fun provideHeroesListFragment()= HeroesListFragment()

    @Singleton
    @Provides
    fun provideHeroesFragment()= HeroesFragment()

}

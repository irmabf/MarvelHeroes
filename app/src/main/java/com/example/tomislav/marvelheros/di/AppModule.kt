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
import android.app.Application
import android.content.Context
import com.example.tomislav.marvelheros.BuildConfig
import com.example.tomislav.marvelheros.data.repository.AuthInterceptor
import com.example.tomislav.marvelheros.data.repository.HeroesByPageKeyRepository
import dagger.Binds
import okhttp3.Cache
import okhttp3.Interceptor
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.Executors


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
    fun provideHeroesListFragment():HeroesListFragment= HeroesListFragment()

    @Singleton
    @Provides
    fun provideHeroesFragment():HeroesFragment= HeroesFragment()

    @Singleton
    @Provides
    fun provideHttpClient(interceptor: Interceptor):OkHttpClient{
        val builder = OkHttpClient().newBuilder().apply {
            addInterceptor(interceptor)
            //cache(cache)
        }
        return builder.build()
    }

     @Singleton
     @Provides
     @JvmSuppressWildcards
     fun provideInterceptor():Interceptor = AuthInterceptor(BuildConfig.PUBLIC_API_KEY,BuildConfig.PRIVATE_API_KEY)


     /*@Singleton
     @Provides
     fun provideCache(file: File):Cache = Cache(file,10*10*1000)

     @Singleton
     @Provides
     fun provideCacheFile(context: Context):File = context.filesDir*/

     @Provides
     fun provideMoshiClient():MoshiConverterFactory= MoshiConverterFactory.create()

    @Singleton
    @Provides
    fun provideHeroesRepository(marvelService: MarvelService, networkExecutor: Executor):HeroesByPageKeyRepository = HeroesByPageKeyRepository(marvelService, networkExecutor)

    @Singleton
    @Provides
    fun provideNetworkExecutor():Executor = Executors.newFixedThreadPool(5)

}

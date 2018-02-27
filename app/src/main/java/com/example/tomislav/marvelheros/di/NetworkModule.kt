package com.example.tomislav.marvelheros.di

import android.content.Context
import com.example.tomislav.marvelheros.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import com.example.tomislav.marvelheros.data.repository.AuthInterceptor
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
class NetworkModule{


    @Singleton
    @Provides
    fun provideHttpClient( cache:Cache):OkHttpClient{
         val builder = OkHttpClient().newBuilder().apply {
            addInterceptor(AuthInterceptor(BuildConfig.PUBLIC_API_KEY,BuildConfig.PRIVATE_API_KEY))
            //cache(cache)
        }
        return builder.build()
    }

   /* @Singleton
    @Provides
    @JvmSuppressWildcards
    internal fun provideInterceptor() = AuthInterceptor(BuildConfig.PUBLIC_API_KEY,BuildConfig.PRIVATE_API_KEY)


    @Singleton
    @Provides
    fun provideCache(file:File) = Cache(file,10*10*1000)

    @Singleton
    @Provides
    fun provideCacheFile(context: Context) = context.filesDir

    @Provides
    fun provideMoshiClient()= MoshiConverterFactory.create()*/

}
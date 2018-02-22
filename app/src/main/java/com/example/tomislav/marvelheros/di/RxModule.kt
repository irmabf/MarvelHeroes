package com.example.tomislav.marvelheros.di

import com.example.tomislav.marvelheros.Utils.rx.AppRxSchedulers
import com.example.tomislav.marvelheros.Utils.rx.RxSchedulers
import dagger.Module
import dagger.Provides

@Module
class RxModule{
    @Provides
    fun provideRxSchedulers():RxSchedulers = AppRxSchedulers()
}
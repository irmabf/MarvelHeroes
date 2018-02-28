package com.example.tomislav.marvelheros.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.io.File

@Module
abstract class ContextModule{
    @Binds
    abstract fun provideContext(application: Application): Context

}
package com.example.tomislav.marvelheros.di

import android.app.Application
import com.example.tomislav.marvelheros.App
import com.example.tomislav.marvelheros.ViewModel.HeroesViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
                        (AppModule::class),
                        (ActivityBuilder::class),
                         (ContextModule::class),
                        (ViewModelModule::class)
                        ])

interface AppComponent:AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

    fun inject( heroesViewModel: HeroesViewModel)


}
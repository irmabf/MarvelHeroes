package com.example.tomislav.marvelheros.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.tomislav.marvelheros.ViewModel.HeroesViewModel
import com.example.tomislav.marvelheros.ViewModel.HeroesViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey( HeroesViewModel::class )
    abstract fun bindHeroesViewModel( mainViewModel: HeroesViewModel ): ViewModel

    @Binds
    abstract fun bindViewModelFactory( factory: HeroesViewModelFactory): ViewModelProvider.Factory

}
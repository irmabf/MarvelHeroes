package com.example.tomislav.marvelheros.di

import com.example.tomislav.marvelheros.ViewModel.HeroesViewModel
import dagger.Component

@Component( modules = arrayOf(ViewModelModule::class))
interface ViewModelComponent {
    // inject your view models
    fun inject( heroesViewModel: HeroesViewModel )
}
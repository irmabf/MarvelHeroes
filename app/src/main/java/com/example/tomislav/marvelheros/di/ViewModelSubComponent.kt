package com.example.tomislav.marvelheros.di

import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent{

    @Subcomponent.Builder
    interface Builder{
        fun build():ViewModelSubComponent
    }

    fun heroesListViewModel():HeroesListViewModel
    fun heroesViewModel():HeroesViewModel
}
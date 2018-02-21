package com.example.tomislav.marvelheros.di

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHeroesFragment():HeroesFragment

    @ContributesAndroidInjector
    abstract fun contributeHeroesListFragment():HeroesListFragment
}
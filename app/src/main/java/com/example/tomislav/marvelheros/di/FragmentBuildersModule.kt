package com.example.tomislav.marvelheros.di

import com.example.tomislav.marvelheros.View.ui.HeroesFragment
import com.example.tomislav.marvelheros.View.ui.HeroesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHeroesListFragment(): HeroesListFragment

    @ContributesAndroidInjector
    abstract fun provideHeroFragmentFactory(): HeroesFragment
}
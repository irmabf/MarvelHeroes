package com.example.tomislav.marvelheros.di

import dagger.android.ContributesAndroidInjector
import com.example.tomislav.marvelheros.View.ui.MainActivity
import com.example.tomislav.marvelheros.ViewModel.HeroesViewModelFactory
import dagger.Module


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class, FragmentBuildersModule::class))
    internal abstract fun bindMainActivity(): MainActivity


}
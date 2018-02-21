package com.example.tomislav.marvelheros.di

import com.example.tomislav.marvelheros.View.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivitiyModule{
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
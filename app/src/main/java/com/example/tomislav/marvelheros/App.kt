package com.example.tomislav.marvelheros

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.example.tomislav.marvelheros.di.DaggerAppComponent


class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out App> {
        return DaggerAppComponent.builder().create(this)
    }
}
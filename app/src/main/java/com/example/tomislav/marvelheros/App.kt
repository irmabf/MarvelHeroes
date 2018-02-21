package com.example.tomislav.marvelheros

import android.app.Activity
import android.app.Application
import com.example.tomislav.marvelheros.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App:Application(), HasActivityInjector{

    @Inject
     lateinit var dispatchingAndroidInjector:DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = dispatchingAndroidInjector
}
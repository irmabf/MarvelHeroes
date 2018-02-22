package com.example.tomislav.marvelheros.Utils.rx

import com.example.tomislav.marvelheros.Utils.rx.AppRxSchedulers.Companion.INTERNET_SCHEDULERS
import java.util.concurrent.Executor
import java.util.concurrent.Executors

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class AppRxSchedulers : RxSchedulers {

    companion object {
        var backgroundExecutor: Executor = Executors.newCachedThreadPool()
        var BACKGROUND_SCHEDULERS = Schedulers.from(backgroundExecutor)
        var internetExecutor: Executor = Executors.newCachedThreadPool()
        var INTERNET_SCHEDULERS = Schedulers.from(internetExecutor)
    }

    override fun runOnBackground(): Scheduler {
        return BACKGROUND_SCHEDULERS
    }

    override fun io() = Schedulers.io()


    override fun compute() = Schedulers.computation()

    override fun androidThread()= AndroidSchedulers.mainThread()

     override fun internet() = INTERNET_SCHEDULERS


}
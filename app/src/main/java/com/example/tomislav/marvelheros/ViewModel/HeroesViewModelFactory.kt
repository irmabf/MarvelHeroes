package com.example.tomislav.marvelheros.ViewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.tomislav.marvelheros.di.ViewModelSubComponent
import javax.inject.Inject

class HeroesViewModelFactory():ViewModelProvider.Factory{

    @Inject
    constructor(viewModelSubComponent: ViewModelSubComponent) : this() {
        //creators = ArrayMap<Class, Callable<out ViewModel>>()
        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        //creators.put(ProjectViewModel::class.java, { viewModelSubComponent.projectViewModel() })
        //creators.put(ProjectListViewModel::class.java, { viewModelSubComponent.projectListViewModel() })
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
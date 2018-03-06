package com.example.tomislav.marvelheros.View.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.tomislav.marvelheros.R
import com.example.tomislav.marvelheros.View.adapter.HeroesAdapter
import com.example.tomislav.marvelheros.ViewModel.HeroesViewModel
import com.example.tomislav.marvelheros.data.model.Models
import com.example.tomislav.marvelheros.data.model.NetworkState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_heroes_list.*
import javax.inject.Inject

class HeroesListFragment(): DaggerFragment() {
     lateinit var model: HeroesViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                     savedInstanceState: Bundle?): View? {





        return inflater.inflate( R.layout.fragment_heroes_list, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = ViewModelProviders.of(this,viewModelFactory).get(HeroesViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initAdapter()
        initSwipeToRefresh()
    }

    private fun initAdapter() {
        val glide = Glide.with(this)
        val adapter = HeroesAdapter(glide, {
            model.retry()
        },activity as MainActivity)
        heroes_list?.layoutManager = LinearLayoutManager(activity)
        heroes_list?.adapter = adapter
        model.heroes.observe(this, Observer<PagedList<Models.Hero>> {
            adapter.setList(it)
        })
        model.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }

    private fun initSwipeToRefresh() {
        model.refreshState.observe(this, Observer {
            swipe_refresh.isRefreshing = it == NetworkState.LOADING
        })
        swipe_refresh.setOnRefreshListener {
            model.refresh()
        }
    }


}
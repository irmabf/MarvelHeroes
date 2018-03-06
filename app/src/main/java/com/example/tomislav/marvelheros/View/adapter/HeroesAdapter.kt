package com.example.tomislav.marvelheros.View.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.tomislav.marvelheros.R
import com.example.tomislav.marvelheros.View.ui.MainActivity
import com.example.tomislav.marvelheros.data.model.Models
import com.example.tomislav.marvelheros.data.model.NetworkState
import android.view.animation.AnimationUtils




class HeroesAdapter(private val glide: RequestManager,
                    private val retryCallback: () -> Unit,
                    private val activity: MainActivity): PagedListAdapter<Models.Hero, RecyclerView.ViewHolder>(HERO_COMPARATOR){

    private var lastPosition:Int = -1
    private var networkState: NetworkState? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.hero_list_item -> HeroViewHolder.create(parent, glide)
            R.layout.network_state_item -> NetworkStateItemViewHolder.create(parent, retryCallback)
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.hero_list_item -> (holder as HeroViewHolder).bind(getItem(position))
            R.layout.network_state_item -> (holder as NetworkStateItemViewHolder).bindTo(networkState)
        }
        Log.d("LIST SIZE: ",this.currentList?.size.toString())
        Log.d("POSITION: ", position.toString())
        if(getItemViewType(position) != R.layout.network_state_item){
            val hero = this.currentList?.get(position)
            holder.itemView.setOnClickListener({activity.apply { hero?.let { activity.show(it) } }})
            setAnimation(holder.itemView, position)
        }
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(activity, android.R.anim.fade_in)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    private fun hasExtraRow() = networkState != null && networkState != NetworkState.LOADED

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            R.layout.network_state_item
        } else {
            R.layout.hero_list_item
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: NetworkState?) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    companion object {
        val HERO_COMPARATOR = object : DiffCallback<Models.Hero>() {
            override fun areContentsTheSame(oldItem: Models.Hero, newItem: Models.Hero): Boolean =
                    oldItem == newItem

            override fun areItemsTheSame(oldItem: Models.Hero, newItem: Models.Hero): Boolean =
                    oldItem.name == newItem.name

        }
    }


}
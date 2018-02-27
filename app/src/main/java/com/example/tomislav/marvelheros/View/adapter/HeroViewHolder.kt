package com.example.tomislav.marvelheros.View.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.tomislav.marvelheros.R
import com.example.tomislav.marvelheros.data.model.Models

class HeroViewHolder(view: View, private val glide: RequestManager)
    : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.title)
    private val thumbnail : ImageView = view.findViewById(R.id.thumbnail)
    private var hero : Models.Hero? = null
    /*init {
        view.setOnClickListener {
            post?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }*/

    fun bind(hero: Models.Hero?) {
        this.hero = hero
        title.text = hero?.name ?: "loading"
        if (hero?.thumbnail?.path?.startsWith("http") == true) {
            thumbnail.visibility = View.VISIBLE
            glide.load(hero.thumbnail.path).centerCrop()
                    .placeholder(R.drawable.ic_insert_photo_black_48dp)
                    .into(thumbnail)
        } else {
            thumbnail.visibility = View.GONE
            Glide.clear(thumbnail)
        }

    }

    companion object {
        fun create(parent: ViewGroup, glide: RequestManager): HeroViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.reddit_post_item, parent, false)
            return HeroViewHolder(view, glide)
        }
    }


}
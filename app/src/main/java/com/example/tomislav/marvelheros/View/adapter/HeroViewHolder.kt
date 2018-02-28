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
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerCrop
import com.bumptech.glide.request.RequestOptions



class HeroViewHolder(view: View, private val glide: RequestManager)
    : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.hero_name)
    private val thumbnail : ImageView = view.findViewById(R.id.hero_image)
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
            val options = RequestOptions()
            options.centerCrop()

            glide.load(hero.thumbnail.getImageUrl()).apply(options)
                    .into(thumbnail)
        } else {
            thumbnail.visibility = View.GONE
            glide.clear(thumbnail)
        }

    }



    companion object {
        fun create(parent: ViewGroup, glide: RequestManager): HeroViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.hero_list_item, parent, false)
            return HeroViewHolder(view, glide)
        }
    }


}
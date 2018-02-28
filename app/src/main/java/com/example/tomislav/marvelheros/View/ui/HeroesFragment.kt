package com.example.tomislav.marvelheros.View.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tomislav.marvelheros.R
import com.example.tomislav.marvelheros.data.model.Models
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_heroes.*

class HeroesFragment() : DaggerFragment() {



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_heroes, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle:Bundle? = this.arguments
        lateinit var hero: Models.Hero

        if (bundle != null) {
            hero = bundle.getParcelable("hero")
        }
        hero_item_name.text=hero.name;
        Glide.with(this).load(hero.thumbnail.getImageUrl()).into(hero_item_image)

        val fab = fab
        fab.setOnClickListener({
            val intent = Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(hero.urls.get(1).url))
            startActivity(intent);
        })
        }



    }


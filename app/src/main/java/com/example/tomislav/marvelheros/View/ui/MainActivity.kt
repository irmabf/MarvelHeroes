package com.example.tomislav.marvelheros.View.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import com.example.tomislav.marvelheros.R
import com.example.tomislav.marvelheros.data.model.Models
import dagger.android.support.DaggerAppCompatActivity



class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            addFragment(HeroesListFragment(),R.id.fragment_container)
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    fun MainActivity.show(hero: Models.Hero) {
        val bundle=Bundle()
        bundle.putParcelable("hero",hero)
        var fragment = HeroesFragment()
        fragment.arguments = bundle
        replaceFragment(fragment,R.id.fragment_container)
    }

    fun DaggerAppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }


    fun DaggerAppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}

    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }


}


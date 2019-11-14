package com.example.helloandroid.verzicht

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.helloandroid.MainActivity
import com.example.helloandroid.R

class VerzichtUebersichtActivity : AppCompatActivity(), VerzichtListFragment.EditVerzichtFragmentOpener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verzicht_uebersicht)

        val verzichtListFragment = VerzichtListFragment()
        verzichtListFragment.openEditVerzichtFragmentCallback = this
        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_verzicht, verzichtListFragment)//
            .commit()
    }

    fun openMainActivity(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun openEditVerzichtFragment() {
        supportFragmentManager.beginTransaction()//
            .replace(R.id.fragment_container_verzicht, EditVerzichtFragment())//
            .addToBackStack("egal")
            .commit()
    }


}

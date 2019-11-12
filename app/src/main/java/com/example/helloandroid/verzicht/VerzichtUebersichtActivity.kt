package com.example.helloandroid.verzicht

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.helloandroid.MainActivity
import com.example.helloandroid.R

class VerzichtUebersichtActivity : AppCompatActivity() {

    private lateinit var viewModel: VerzichtUebersichtViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verzicht_uebersicht)

        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_verzicht,VerzichtListFragment())//
            .commit()
    }

    fun openMainActivity(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }


}
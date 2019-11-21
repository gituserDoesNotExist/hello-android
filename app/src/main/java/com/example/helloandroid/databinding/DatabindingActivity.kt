package com.example.helloandroid.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloandroid.R

class DatabindingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_databinding)

        val databindingFragment = DatabindingFragment()
        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_ausgaben, databindingFragment)//
            .commit()
    }
}

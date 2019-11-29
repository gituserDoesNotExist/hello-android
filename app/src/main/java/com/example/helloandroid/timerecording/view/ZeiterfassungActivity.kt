package com.example.helloandroid.timerecording.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.helloandroid.R

class ZeiterfassungActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zeiterfassung)

        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_zeiterfassung, ZeiterfassungFragment())//
            .commit()
    }
}

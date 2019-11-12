package com.example.helloandroid.fragmentsht

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.helloandroid.R

class FunWithFragmentsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fun_with_fragments)

        supportFragmentManager.beginTransaction().add(R.id.input_fragment,InputFragment()).commit()
        supportFragmentManager.beginTransaction().add(R.id.output_fragment,OutputFragment()).commit()
    }
}

package com.example.helloandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var countViewModel: CountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startVerzicht(currentView: View) {
        startActivity(Intent(this,VerzichtUebersichtActivity::class.java))
    }

    fun startCount(currentView: View) {
        startActivity(Intent(this,CountActivity::class.java))
    }


}

package com.example.helloandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var counterViewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counterView = findViewById<TextView>(R.id.counter_view)
        counterViewModel = ViewModelProviders.of(this).get(CounterViewModel::class.java)

        val counterObserver = Observer<Int> { countValue -> counterView.text = countValue.toString()}
        counterViewModel.counterLiveData.observe(this,counterObserver)
    }

    fun toastMe(currentView: View) : Unit {
        Toast.makeText(this, "Hello world",Toast.LENGTH_SHORT).show()
    }

    fun countIt(currentView: View) {
        counterViewModel.increaseCounterBy1()
    }

    fun startVerzicht(currentView: View) {
        val intent = Intent(this,VerzichtUebersichtActivity::class.java)
        startActivity(intent)
    }


}

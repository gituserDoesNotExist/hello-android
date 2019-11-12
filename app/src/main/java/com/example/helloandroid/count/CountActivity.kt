package com.example.helloandroid.count

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.MainActivity
import com.example.helloandroid.R

class CountActivity : AppCompatActivity() {

    private lateinit var countViewModel: CountViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count)
        
        val counterView = findViewById<TextView>(R.id.counter_view)
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

        val counterObserver = Observer<Int> { countValue -> counterView.text = countValue.toString()}
        countViewModel.counterLiveData.observe(this,counterObserver)
    }

    fun countIt(currentView: View) {
        countViewModel.increaseCounterBy1()
    }

    fun openMainActivity(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

}

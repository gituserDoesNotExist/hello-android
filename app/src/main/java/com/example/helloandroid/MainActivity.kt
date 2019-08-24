package com.example.helloandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toastMe(currentView: View) : Unit {
        Toast.makeText(this, "Hello world",Toast.LENGTH_SHORT).show()
    }

    fun countIt(currentView: View) {
        val idTextView = R.id.textView
        val textView: TextView = findViewById(idTextView)
        val text: String = textView.text.toString()

        var currentValueOfCounter: Int = Integer.parseInt(text)
        currentValueOfCounter++

        textView.text = currentValueOfCounter.toString()
    }

    fun startVerzicht(currentView: View) {
        val intent: Intent = Intent(this,ChocolateActivity::class.java)
        startActivity(intent)
    }


}

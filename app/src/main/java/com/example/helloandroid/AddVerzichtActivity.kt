package com.example.helloandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.helloandroid.persistence.AppDatabase
import com.example.helloandroid.persistence.Verzicht

import java.time.LocalDateTime

class AddVerzichtActivity : AppCompatActivity() {

    private lateinit var verzichtService: VerzichtService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_verzicht)



        verzichtService = VerzichtService(AppDatabase.getDb(applicationContext).verzichtDao())

    }

    fun saveVerzicht(view: View) {
        println("save verzicht")
        val verzichtName = findViewById<EditText>(R.id.input_verzicht_name).text.toString()
        verzichtService.addNewVerzicht(Verzicht(verzichtName,0, LocalDateTime.now()))

        startActivity(Intent(this,VerzichtUebersichtActivity::class.java))
    }

}

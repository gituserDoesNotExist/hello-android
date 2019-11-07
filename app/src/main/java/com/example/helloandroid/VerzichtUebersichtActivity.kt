package com.example.helloandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.lifecycle.Observer
import com.example.helloandroid.persistence.AppDatabase
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.presentation.VerzichtArrayAdapter
import java.util.stream.Collectors

class VerzichtUebersichtActivity : AppCompatActivity() {

    private lateinit var verzichtService: VerzichtService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verzicht_uebersicht)
        verzichtService = VerzichtService(AppDatabase.getDb(applicationContext).verzichtDao())

        val verzichte = verzichtService.findAllVerzichteLiveData()
        val verzichtListView = findViewById<ListView>(R.id.list_view)


        val verzichteObserver =
            Observer<List<Verzicht>> { verzichte ->
                if (verzichte.isEmpty()) {
                    verzichtListView.visibility = ListView.INVISIBLE
                }
                verzichtListView.adapter = VerzichtArrayAdapter(this, verzichte)
            }

        verzichte.observe(this, verzichteObserver)

    }

    fun newVerzicht(view: View) {
        val intent: Intent = Intent(this, AddVerzichtActivity::class.java)
        startActivity(intent)
    }

    fun openVerzicht(view: View) {
        println("open verzicht")
    }
}

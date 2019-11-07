package com.example.helloandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders.*
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.presentation.VerzichtArrayAdapter
import java.time.LocalDateTime
import java.util.function.Consumer

class VerzichtUebersichtActivity : AppCompatActivity() {

    private lateinit var viewModel: VerzichtUebersichtViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verzicht_uebersicht)

        val verzichtListView = findViewById<ListView>(R.id.list_view)
        viewModel = of(this, VerzichtViewModelFactory(application)).get(VerzichtUebersichtViewModel::class.java)
        viewModel.initializeByLoadingAllVerzichte()


        val verzichteObserver = Observer<List<Verzicht>> {
            if (it.isEmpty()) {
                verzichtListView.visibility = ListView.INVISIBLE
            }
            verzichtListView.adapter = VerzichtArrayAdapter(this, it, Consumer { t -> viewModel.deleteVerzicht(t) })
        }
        viewModel.verzichteLiveData.observe(this, verzichteObserver)
    }

    fun createNewVerzicht(view: View) {
        val verzichtName = findViewById<EditText>(R.id.input_new_verzicht_name).text.toString()
        viewModel.saveVerzicht(Verzicht(verzichtName,0, LocalDateTime.now()))
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    fun openMainActivity(view: View) {
        startActivity(Intent(this,MainActivity::class.java))
    }


}
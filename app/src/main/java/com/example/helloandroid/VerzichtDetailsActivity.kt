package com.example.helloandroid

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.persistence.AppDatabase
import com.example.helloandroid.persistence.TypeNotFoundException
import com.example.helloandroid.persistence.Verzicht

class VerzichtDetailsActivity : AppCompatActivity() {

    companion object {
        private const val ERROR_NO_VERZICHT = "Es wurde kein Name eingegeben, Activity kann nicht gestartet werden"
    }

    private lateinit var verzichtDetailsViewModel: VerzichtDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verzicht_details)

        val verzichtName = extractVerzichtName()
        verzichtName ?: throw TypeNotFoundException(ERROR_NO_VERZICHT)

        val verzichtDao = AppDatabase.getDb(this).verzichtDao()
        verzichtDetailsViewModel =
            ViewModelProviders.of(this, VerzichtViewModelFactory(application)).get(VerzichtDetailsViewModel::class.java)

        verzichtDetailsViewModel.findByVerzichtName(verzichtName)
        val verzichtLiveData = verzichtDetailsViewModel.getCurrentVerzicht()
        val verzichtObserver = Observer<Verzicht> { verzicht ->
            updateDescription(verzicht)
        }
        verzichtLiveData.observe(this, verzichtObserver)
    }

    private fun extractVerzichtName(): String? {
        return intent.getStringExtra(IntentKeys.VERZICHT_NAME.name)
    }

    private fun updateDescription(verzicht: Verzicht) {
        val descriptionVerzichtDetails: String =
            resources.getString(R.string.verzicht_details_message, verzicht.verzichtName)
        findViewById<TextView>(R.id.description_verzicht_details).text = descriptionVerzichtDetails
        findViewById<TextView>(R.id.verzicht_duration).text = verzicht.days.toString()
    }

    fun increaseDaysWithoutChocolateByOneDay(view: View) {
        verzichtDetailsViewModel.increaseVerzichtDurationByOneDay()
    }

    private fun updateVerzichtDuration(duration: String) {
        findViewById<TextView>(R.id.verzicht_duration).text = duration
    }

}

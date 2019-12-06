package com.example.helloandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.helloandroid.count.CountActivity
import com.example.helloandroid.databinding.DatabindingActivity
import com.example.helloandroid.finances.view.FinancesActivity
import com.example.helloandroid.fragmentsht.FunWithFragmentsActivity
import com.example.helloandroid.timerecording.view.ZeiterfassungActivity
import com.example.helloandroid.verzicht.view.VerzichtUebersichtActivity
import com.jakewharton.threetenabp.AndroidThreeTen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Dashbaord"
    }

    fun startVerzicht(currentView: View) {
        startActivity(Intent(this, VerzichtUebersichtActivity::class.java))
    }

    fun startCount(currentView: View) {
        startActivity(Intent(this, CountActivity::class.java))
    }

    fun startFragmentPlayground(currentView: View) {
        startActivity(Intent(this, FunWithFragmentsActivity::class.java))
    }

    fun startFincancesActivity(view: View) {
        startActivity(Intent(this, FinancesActivity::class.java))
    }

    fun startDatabindingActivity(view: View) {
        startActivity(Intent(this, DatabindingActivity::class.java))
    }

    fun startTimerecordingActivity(view: View) {
        startActivity(Intent(this, ZeiterfassungActivity::class.java))
    }

}

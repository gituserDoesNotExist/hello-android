package com.example.helloandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.helloandroid.count.CountActivity
import com.example.helloandroid.count.CountViewModel
import com.example.helloandroid.databinding.DatabindingActivity
import com.example.helloandroid.finances.view.FinancesActivity
import com.example.helloandroid.fragmentsht.FunWithFragmentsActivity
import com.example.helloandroid.verzicht.VerzichtUebersichtActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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


}

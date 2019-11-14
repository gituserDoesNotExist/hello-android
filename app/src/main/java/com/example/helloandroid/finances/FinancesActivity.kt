package com.example.helloandroid.finances

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.helloandroid.R

class FinancesActivity : FragmentActivity(), AusgabenUebersichtFragment.PostenDetailsFragmentOpener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finances)

        val ausgabenUebersichtFragment = AusgabenUebersichtFragment()
        ausgabenUebersichtFragment.openFragmentCallback = this
        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_ausgaben, ausgabenUebersichtFragment)//
            .addToBackStack(null)//
            .commit()
    }

    override fun openPostenDetailsFragment() {
        supportFragmentManager.beginTransaction()//
            .replace(R.id.fragment_container_ausgaben, PostenDetailsFragment())//
            .addToBackStack(null)//
            .commit()
    }
}

package com.example.helloandroid.finances.view

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.helloandroid.R

class FinancesActivity : FragmentActivity(),
    PostenUebersichtFragment.PostenDetailsFragmentOpener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finances)

        val postenUebersichtFragment = PostenUebersichtFragment()
        postenUebersichtFragment.openFragmentCallback = this
        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_ausgaben, postenUebersichtFragment)//
            .commit()
    }

    override fun openPostenDetailsFragment() {
        supportFragmentManager.beginTransaction()//
            .replace(R.id.fragment_container_ausgaben, PostenDetailsFragment())//
            .addToBackStack(null)//
            .commit()
    }
}

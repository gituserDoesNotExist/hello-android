package com.example.helloandroid.timerecording.view

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.R

class ZeiterfassungActivity : AppCompatActivity(), AppConfigurationFragment.OnFragmentInteractionListener {

    override fun exitAppConfigFragment() {
        supportFragmentManager.beginTransaction()//
            .replace(R.id.fragment_container_zeiterfassung,ArbeitsverhaltnisUebersichtFragment())//
            .commit()
        supportActionBar?.title = "Zeiterfassung"
    }

    override fun setFragmentTitle(value: String) {
        supportActionBar?.title = value
    }

    private lateinit var startZeiterfassungViewModel: StartZeiterfassungViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zeiterfassung)
        configureToolbar()
        initializeViewModel()

        startZeiterfassungViewModel.existsConfiguration.observe(this, Observer { appConfigured ->
            if (appConfigured) {
                showFragment(AppConfigurationFragment())
            } else {
                showFragment(AppConfigurationFragment())
            }
        })
    }

    private fun configureToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        toolbar.title = "Zeiterfassung"
        setSupportActionBar(toolbar)
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_TITLE
    }


    private fun initializeViewModel() {
        startZeiterfassungViewModel = ViewModelProviders.of(this, ConfigViewModelFactory(this.application))
            .get(StartZeiterfassungViewModel::class.java)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_zeiterfassung, fragment)//
            .commit()
    }
}

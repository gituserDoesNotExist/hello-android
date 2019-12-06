package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis

class ZeiterfassungActivity : AppCompatActivity(), AppConfigurationFragment.OnFragmentInteractionListener,
    ArbeitsverhaltnisUebersichtFragment.OnFragmentInteractionListener {

    private lateinit var sharedArbeitsverhaeltnisViewModel: SharedArbeitsverhaeltnisViewModel
    private var editArbeitsverhaeltnis: Boolean = false

    override fun openArbeitsverhaeltnisDetails(arbeitsverhaeltnis: Arbeitsverhaeltnis) {
        sharedArbeitsverhaeltnisViewModel.currentArbeitsverhaeltnis = arbeitsverhaeltnis
        supportFragmentManager.beginTransaction()//
            .replace(R.id.fragment_container_zeiterfassung, ArbeitsverhaeltnisDetailsFragment())//
            .commit()
        supportActionBar?.title = arbeitsverhaeltnis.createTitleForArbeitsverhaeltnis()
        editArbeitsverhaeltnis = true
        invalidateOptionsMenu()
    }

    override fun exitAppConfigFragment() {
        supportFragmentManager.beginTransaction()//
            .replace(R.id.fragment_container_zeiterfassung, ArbeitsverhaltnisUebersichtFragment())//
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
        supportActionBar?.title = "Zeiterfassung"
        initializeViewModel()

        startZeiterfassungViewModel.existsConfiguration.observe(this, Observer { appConfigured ->
            if (appConfigured) {
                showFragment(AppConfigurationFragment())
            } else {
                showFragment(AppConfigurationFragment())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (editArbeitsverhaeltnis) {
            menuInflater.inflate(R.menu.menu_arbeitsverhaeltnis_details, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit_arbeitsverhaeltnis -> println("hello world")
            R.id.action_delete_arbeitsverhaeltnis -> sharedArbeitsverhaeltnisViewModel.deleteArbeitsverhaeltnis()
        }
        return true
    }

    private fun initializeViewModel() {
        startZeiterfassungViewModel = ViewModelProviders.of(this, ConfigViewModelFactory(this.application))
            .get(StartZeiterfassungViewModel::class.java)
        sharedArbeitsverhaeltnisViewModel =
            ViewModelProviders.of(this,ArbeitsverhaeltnisViewModelFactory()).get(SharedArbeitsverhaeltnisViewModel::class.java)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_zeiterfassung, fragment)//
            .commit()
    }
}

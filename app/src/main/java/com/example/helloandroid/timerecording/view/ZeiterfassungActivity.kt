package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.TeamupEvent

class ZeiterfassungActivity : AppCompatActivity(), AppConfigurationFragment.OnFragmentInteractionListener,
    ArbeitsverhaltnisUebersichtFragment.OnFragmentInteractionListener {

    private lateinit var sharedTeamupEventViewModel: SharedTeamupEventViewModel
    private var arbeitsverhaeltnisDetails: Boolean = false

    override fun openArbeitsverhaeltnisDetailsFragment(teamupEvent: TeamupEvent) {
        sharedTeamupEventViewModel.currentEvent = teamupEvent
        replaceFragment(EditArbeitsverhaeltnisDetailsFragment.newInstance(false))
        supportActionBar?.title = teamupEvent.arbeitsverhaeltnis.kategorie
        arbeitsverhaeltnisDetails = true
        invalidateOptionsMenu()
    }

    override fun exitAppConfigFragment() {
        openArbeitsverhaeltnisUebersichtFragment()
    }

    private fun openArbeitsverhaeltnisUebersichtFragment() {
        replaceFragment(ArbeitsverhaltnisUebersichtFragment())
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
        if (arbeitsverhaeltnisDetails) {
            menuInflater.inflate(R.menu.menu_arbeitsverhaeltnis_details, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_edit_arbeitsverhaeltnis -> openEditArbeitsverhaeltnisDetailsFragment()
            R.id.action_delete_arbeitsverhaeltnis -> {
                sharedTeamupEventViewModel.deleteArbeitsverhaeltnis()
                openArbeitsverhaeltnisUebersichtFragment()
            }
        }
        return true
    }

    private fun openEditArbeitsverhaeltnisDetailsFragment() {
        replaceFragment(EditArbeitsverhaeltnisDetailsFragment.newInstance(true))
        arbeitsverhaeltnisDetails = true
        invalidateOptionsMenu()
    }


    private fun initializeViewModel() {
        startZeiterfassungViewModel = ViewModelProviders.of(this, ZeiterfassungViewModelFactory(this.application))
            .get(StartZeiterfassungViewModel::class.java)
        sharedTeamupEventViewModel = ViewModelProviders.of(this, ZeiterfassungViewModelFactory(this.application))
            .get(SharedTeamupEventViewModel::class.java)
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()//
            .add(R.id.fragment_container_zeiterfassung, fragment)//
            .commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()//
            .replace(R.id.fragment_container_zeiterfassung, fragment)//
            .addToBackStack(null)//
            .commit()
    }
}

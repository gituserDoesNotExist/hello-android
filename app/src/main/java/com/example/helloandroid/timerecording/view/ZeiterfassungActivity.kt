package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.TeamupEvent

class ZeiterfassungActivity : AppCompatActivity(), AppConfigurationFragment.OnFragmentInteractionListener,
    ArbeitsverhaltnisUebersichtFragment.OnFragmentInteractionListener,
    EditArbeitsverhaeltnisDetailsFragment.FragmentInteractionListener {

    private lateinit var sharedTeamupEventViewModel: SharedTeamupEventViewModel
    private var arbeitsverhaeltnisDetails: Boolean = false
    private lateinit var currentFragmentTag: String

    companion object {
        private const val CURRENT_FRAGMENT = "KEY_CURRENT_FRAGMENT"
        private const val ARBEITSVERHAELTNIS_UEBERSICHT = "ARBEITSVERHAELTNIS_UEBERSICHT"
        private const val APP_CONFIGURATION = "APP_CONFIGURATION"
        private const val EDIT_ARBEITSVERHAELTNIS_DETAILS = "EDIT_ARBEITSVERHAELTNIS_DETAILS"
        private const val ARBEITSVERHAELTNIS_DETAILS = "ARBEITSVERHAELTNIS_DETAILS"
    }

    override fun onUpdateArbeitsverhaeltnis() {
        openArbeitsverhaeltnisUebersichtFragment()
    }

    override fun openArbeitsverhaeltnisDetailsFragment(teamupEvent: TeamupEvent) {
        sharedTeamupEventViewModel.currentEvent = teamupEvent
        openArbeitsverhaeltnisDetailsFragmentWithEventFromSharedViewModel()
    }

    private fun openArbeitsverhaeltnisDetailsFragmentWithEventFromSharedViewModel() {
        replaceFragment(EditArbeitsverhaeltnisDetailsFragment.newInstance(false), ARBEITSVERHAELTNIS_DETAILS)
        arbeitsverhaeltnisDetails = true
        invalidateOptionsMenu()
    }

    override fun exitAppConfigFragment() {
        openArbeitsverhaeltnisUebersichtFragment()
    }

    private fun openArbeitsverhaeltnisUebersichtFragment() {
        replaceFragment(ArbeitsverhaltnisUebersichtFragment(), ARBEITSVERHAELTNIS_UEBERSICHT)
    }


    private lateinit var startZeiterfassungViewModel: StartZeiterfassungViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zeiterfassung)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_zeiterfassung).apply { this.title = activityTitle() })
//        initializeViewModel()
//
//        startZeiterfassungViewModel.existsConfiguration.observe(this, Observer { appConfigured ->
//            if (savedInstanceState == null) {
//                chooseFragment(appConfigured)
//            } else {
//                val currentFragment = savedInstanceState.getString(CURRENT_FRAGMENT)
//                currentFragment?.let {
//                    when (it) {
//                        ARBEITSVERHAELTNIS_UEBERSICHT -> openArbeitsverhaeltnisUebersichtFragment()
//                        EDIT_ARBEITSVERHAELTNIS_DETAILS -> openEditArbeitsverhaeltnisDetailsFragment()
//                        ARBEITSVERHAELTNIS_DETAILS -> openArbeitsverhaeltnisDetailsFragmentWithEventFromSharedViewModel()
//                        APP_CONFIGURATION -> openAppConfigurationFragment()
//                    }
//                }
//            }
//        })
    }

    private fun activityTitle() = resources.getString(R.string.title_activity_zeiterfassung)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(CURRENT_FRAGMENT, currentFragmentTag)
    }

    private fun chooseFragment(appConfigured: Boolean) {
        if (appConfigured) {
            openArbeitsverhaeltnisUebersichtFragment()
        } else {
            openAppConfigurationFragment()
        }
    }

    private fun openAppConfigurationFragment() {
        replaceFragment(AppConfigurationFragment(), APP_CONFIGURATION)
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
        replaceFragment(EditArbeitsverhaeltnisDetailsFragment.newInstance(true), EDIT_ARBEITSVERHAELTNIS_DETAILS)
        arbeitsverhaeltnisDetails = true
        invalidateOptionsMenu()
    }


    private fun initializeViewModel() {
        startZeiterfassungViewModel = ViewModelProviders.of(this, ZeiterfassungViewModelFactory(this.application))
            .get(StartZeiterfassungViewModel::class.java)
        sharedTeamupEventViewModel = ViewModelProviders.of(this, ZeiterfassungViewModelFactory(this.application))
            .get(SharedTeamupEventViewModel::class.java)
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        currentFragmentTag = tag
//        supportFragmentManager.beginTransaction()//
//            .replace(R.id.fragment_container_zeiterfassung, fragment, tag)//
//            .addToBackStack(null)//
//            .commit()
    }
}

package com.example.helloandroid.timerecording.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.helloandroid.MainActivity
import com.example.helloandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class ZeiterfassungActivity : AppCompatActivity(), EditArbeitsverhaeltnisDetailsFragment.FragmentInteractionListener {

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


    private fun openArbeitsverhaeltnisUebersichtFragment() {
        replaceFragment(ArbeitsverhaltnisUebersichtFragment(), ARBEITSVERHAELTNIS_UEBERSICHT)
    }


    private lateinit var startZeiterfassungViewModel: StartZeiterfassungViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zeiterfassung)
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_zeiterfassung).apply { this.title = activityTitle() })


        findViewById<BottomNavigationView>(
            R.id.bottom_navigation_zeiterfassung).setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_home -> {
                    goToHome()
                }
            }
            true
        }
    }


    private fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun activityTitle() = resources.getString(R.string.title_activity_zeiterfassung)


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

    private fun replaceFragment(fragment: Fragment, tag: String) {
        currentFragmentTag = tag
    }
}

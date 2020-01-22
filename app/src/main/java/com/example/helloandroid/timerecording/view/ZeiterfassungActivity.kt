package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.R
import com.example.helloandroid.R.navigation.navigation_zeiterfassung
import com.example.helloandroid.timerecording.KeineAuswahl
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jakewharton.threetenabp.AndroidThreeTen


class ZeiterfassungActivity : BaseActivity() {

    private lateinit var startZeiterfassungViewModel: StartZeiterfassungViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        KeineAuswahl.init(this)
        initViewModel()
        setContentView(R.layout.activity_zeiterfassung)

        startZeiterfassungViewModel.existsConfiguration.observe(this, Observer {
            configureToolbar()
            configureNavHostFragment(it)
            configureBottomNavigationView()
        })
    }

    override fun getParentViewForSnackbar(): View {
        return findViewById(android.R.id.content)
    }

    private fun initViewModel() {
        startZeiterfassungViewModel = ViewModelProviders.of(this, ZeiterfassungViewModelFactory(this.application))
            .get(StartZeiterfassungViewModel::class.java)
    }

    private fun configureToolbar() {
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_zeiterfassung).apply {
            this.title = activityTitle()
        })
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun configureNavHostFragment(existsConfiguration: Boolean) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navInflater = navHostFragment.navController.navInflater
        val navGraph = navInflater.inflate(navigation_zeiterfassung)
        if (existsConfiguration) {
            navGraph.startDestination = R.id.arbeitsverhaltnisUebersichtFragment
        } else {
            navGraph.startDestination = R.id.appConfigurationFragment
        }
        navHostFragment.navController.graph = navGraph
    }

    private fun configureBottomNavigationView() {
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


    private fun activityTitle() = resources.getString(R.string.title_activity_zeiterfassung)


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_zeiterfassung, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        when (item.itemId) {
            R.id.action_open_settings -> ZeiterfassungNavigation.getNavigation(navHostFragment.navController).toConfig()
        }
        return false
    }
}

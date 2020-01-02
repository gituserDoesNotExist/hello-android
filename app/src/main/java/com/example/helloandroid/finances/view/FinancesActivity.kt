package com.example.helloandroid.finances.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.example.helloandroid.MainActivity
import com.example.helloandroid.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class FinancesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finances)

        configureToolbar()
        configureNavHostFragment()
        configureBottomNavigationView()

    }

    private fun configureToolbar() {
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_finances).apply {
            this.title = resources.getString(R.string.title_activity_finances)
        })
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun configureNavHostFragment() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_finances) as NavHostFragment
        val navInflater = navHostFragment.navController.navInflater
        val navGraph = navInflater.inflate(R.navigation.navigation_finances)
        navHostFragment.navController.graph = navGraph
    }


    private fun configureBottomNavigationView() {
        findViewById<BottomNavigationView>(
            R.id.bottom_navigation_finances).setOnNavigationItemSelectedListener { menuItem ->
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


}

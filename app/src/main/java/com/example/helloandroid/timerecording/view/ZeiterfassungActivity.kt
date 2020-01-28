package com.example.helloandroid.timerecording.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import com.example.helloandroid.BaseActivity
import com.example.helloandroid.R
import com.example.helloandroid.timerecording.KeineAuswahl
import com.jakewharton.threetenabp.AndroidThreeTen


class ZeiterfassungActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidThreeTen.init(this)
        KeineAuswahl.init(this)
        setContentView(R.layout.activity_zeiterfassung)

        configureToolbar()
    }

    override fun getParentViewForSnackbar(): View {
        return findViewById(android.R.id.content)
    }


    private fun configureToolbar() {
        setSupportActionBar(findViewById<Toolbar>(R.id.toolbar_zeiterfassung).apply {
            this.title = activityTitle()
        })
    }

    private fun activityTitle() = resources.getString(R.string.title_activity_zeiterfassung)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        return true
    }


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

package com.example.helloandroid.timerecording.view

import androidx.navigation.NavController
import com.example.helloandroid.NavigationZeiterfassungDirections

class ZeiterfassungNavigation private constructor(private val navController: NavController) {


    companion object {

        fun getNavigation(navController: NavController): ZeiterfassungNavigation {
            return ZeiterfassungNavigation(navController)
        }
    }


    fun fromUebersichtToAddArbeitsverhaeltnis() {
        val action =
            ArbeitsverhaltnisUebersichtFragmentDirections.actionArbeitsverhaltnisUebersichtFragmentToAddArbeitsverhaeltnisFragment2()
        navController.navigate(action)
    }

    fun fromUebersichtToEditZeitArbeitsverhaeltnis() {
        val action =
            ArbeitsverhaltnisUebersichtFragmentDirections.actionArbeitsverhaltnisUebersichtFragmentToEditArbeitsverhaeltnisDetailsFragment()
        navController.navigate(action)
    }

    fun fromUebersichtToEditStueckArbeitsverhaeltnis() {
        val action =
            ArbeitsverhaltnisUebersichtFragmentDirections.actionArbeitsverhaltnisUebersichtFragmentToEditStueckArbeitsverhaeltnisFragment()
        navController.navigate(action)
    }


    fun fromUebersichtToSuchfilter() {
        val action =
            ArbeitsverhaltnisUebersichtFragmentDirections.actionArbeitsverhaltnisUebersichtFragmentToSuchfilterFragment()
        navController.navigate(action)
    }


    fun toConfig() {
        val action = NavigationZeiterfassungDirections.actionGlobalAppConfigurationFragment()
        navController.navigate(action)
    }


    fun toUebersicht() {
        val action = NavigationZeiterfassungDirections.actionGlobalArbeitsverhaltnisUebersichtFragment()
        navController.navigate(action)
    }


}
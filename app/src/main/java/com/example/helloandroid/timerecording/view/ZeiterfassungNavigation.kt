package com.example.helloandroid.timerecording.view

import androidx.navigation.NavController
import com.example.helloandroid.NavigationZeiterfassungDirections
import com.example.helloandroid.WelcomeZeiterfassungFragmentDirections

class ZeiterfassungNavigation private constructor(private val navController: NavController) {


    companion object {

        fun getNavigation(navController: NavController): ZeiterfassungNavigation {
            return ZeiterfassungNavigation(navController)
        }
    }

    fun fromWelcomeToConfig() {
        val action =
            WelcomeZeiterfassungFragmentDirections.actionWelcomeZeiterfassungFragmentToAppConfigurationFragment()
        navController.navigate(action)
    }

    fun fromWelcomeToUebersicht() {
        val action =
            WelcomeZeiterfassungFragmentDirections.actionWelcomeZeiterfassungFragmentToArbeitsverhaltnisUebersichtFragment()
        navController.navigate(action)
    }

    fun fromUebersichtToAddArbeitsverhaeltnis() {
        val action =
            ArbeitsverhaltnisUebersichtFragmentDirections.actionArbeitsverhaltnisUebersichtFragmentToAddArbeitsverhaeltnisFragment2()
        navController.navigate(action)
    }

    fun fromAddZeitArbeitsverhaeltnisToUebersicht() {
        val action =
            AddArbeitsverhaeltnisFragmentDirections.actionAddArbeitsverhaeltnisFragmentToArbeitsverhaltnisUebersichtFragment()
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

    fun fromUpdateZeitArbeitsverhaeltnisTouebersicht() {
        val action =
            EditZeitArbeitsverhaeltnisFragmentDirections.actionEditZeitArbeitsverhaeltnisDetailsFragmentToArbeitsverhaltnisUebersichtFragment()
        navController.navigate(action)
    }

    fun fromUpdateStueckArbeitsverhaeltnisTouebersicht() {
        val action =
            EditStueckArbeitsverhaeltnisFragmentDirections.actionEditStueckArbeitsverhaeltnisFragmentToArbeitsverhaltnisUebersichtFragment()
        navController.navigate(action)
    }


    fun fromUebersichtToSuchfilter() {
        val action =
            ArbeitsverhaltnisUebersichtFragmentDirections.actionArbeitsverhaltnisUebersichtFragmentToSuchfilterFragment()
        navController.navigate(action)
    }

    fun fromSuchfilterToUebersicht() {
        val action = SuchfilterFragmentDirections.actionSuchfilterFragmentToArbeitsverhaltnisUebersichtFragment()
        navController.navigate(action)
    }


    fun fromEditStueckArbeitsverhaeltnisToUebersicht() {
        val action =
            EditStueckArbeitsverhaeltnisFragmentDirections.actionEditStueckArbeitsverhaeltnisFragmentToArbeitsverhaltnisUebersichtFragment()
        navController.navigate(action)
    }


    fun toConfig() {
        val action = NavigationZeiterfassungDirections.actionGlobalAppConfigurationFragment()
        navController.navigate(action)
    }

    fun fromAddStueckArbeitsverhaeltnisToUebersicht() {
        val action =
            AddArbeitsverhaeltnisFragmentDirections.actionAddArbeitsverhaeltnisFragmentToArbeitsverhaltnisUebersichtFragment()
        navController.navigate(action)
    }


}
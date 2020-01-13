package com.example.helloandroid.finances.view

import androidx.navigation.NavController

class FinancesNavigation private constructor(private val navController: NavController) {


    companion object {

        fun getNavigation(navController: NavController): FinancesNavigation {
            return FinancesNavigation(navController)
        }
    }

    fun fromUebersichtToDetails() {
        val action = PostenUebersichtFragmentDirections.actionPostenUebersichtFragmentToPostenDetailsFragment()
        navController.navigate(action)
    }

    fun fromDetailsToUebersicht() {
        val action = PostenDetailsFragmentDirections.actionPostenDetailsFragmentToPostenUebersichtFragment()
        navController.navigate(action)
    }

}
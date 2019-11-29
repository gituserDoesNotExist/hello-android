package com.example.helloandroid.finances.view

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.finances.persistence.FinancesRepository
import com.example.helloandroid.persistence.AppDatabase

/** Factory um ViewModels anzulegen, welche das VerzichtDao benoetigen */
class FinancesViewModelFactory(private val context: Context) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val postenDao = AppDatabase.getDb(context).postenDao()
        val ausgabeDao= AppDatabase.getDb(context).ausgabeDao()
        val postenWithAusgabenDao= AppDatabase.getDb(context).postenWithAusgabeDao()
        val financesRepository = FinancesRepository(postenDao, ausgabeDao,postenWithAusgabenDao)

        return modelClass.getConstructor(FinancesRepository::class.java).newInstance(financesRepository)
    }

}
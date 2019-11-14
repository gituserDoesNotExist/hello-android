package com.example.helloandroid.finances

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloandroid.finances.persistence.FinancesRepository
import com.example.helloandroid.persistence.AppDatabase
import com.example.helloandroid.verzicht.persistence.VerzichtRepository

/** Factory um ViewModels anzulegen, welche das VerzichtDao benoetigen */
class FinancesViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val context: Context = context

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val postenDao = AppDatabase.getDb(context).postenDao()
        val ausgabeDao= AppDatabase.getDb(context).ausgabeDao()
        val financesRepository = FinancesRepository(postenDao, ausgabeDao)

        return modelClass.getConstructor(FinancesRepository::class.java).newInstance(financesRepository)
    }

}
package com.example.helloandroid.verzicht.persistence

import android.database.SQLException
import androidx.lifecycle.LiveData
import com.example.helloandroid.DatabaseAsyncTask
import com.example.helloandroid.DatabaseOperationException


class VerzichtRepository(private var verzichtDao: VerzichtDao) {

    fun findById(id: Long): LiveData<Verzicht> {
        return verzichtDao.getById(id)
    }

    fun findAllVerzichte(): LiveData<List<Verzicht>> {
        return verzichtDao.getAll()
    }

    fun updateVerzicht(verzicht: Verzicht) {
        DatabaseAsyncTask(verzichtDao::updateVerzicht).execute(verzicht)
    }

    fun saveVerzicht(verzicht: Verzicht): Long {
        try {
            return verzichtDao.insertVerzicht(verzicht)
        } catch (e: SQLException) {
            throw DatabaseOperationException("Fehler beim Einfügen von \"${verzicht.verzichtName}\": ${e.message ?: ""}", e)
        } catch (e: Exception) {
            throw DatabaseOperationException("Unknown error")
        }

    }

    fun deleteVerzicht(verzicht: Verzicht) {
        DatabaseAsyncTask(verzichtDao::deleteVerzicht).execute(verzicht)
    }

}
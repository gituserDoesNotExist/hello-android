package com.example.helloandroid.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class VerzichtRepository(verzichtDao: VerzichtDao) : ViewModel() {

    private var verzichtDao = verzichtDao

    fun findByVerzichtName(name: String): LiveData<Verzicht> {
        return verzichtDao.getByName(name)
    }

    fun findById(id: Long): LiveData<Verzicht> {
        return verzichtDao.getById(id)
    }

    fun findAllVerzichte(): LiveData<List<Verzicht>> {
        return verzichtDao.getAll()
    }

    fun updateVerzicht(verzicht: Verzicht) {
        Thread { verzichtDao.updateVerzicht(verzicht) }.start()
    }

    fun saveVerzicht(verzicht: Verzicht) {
        Thread { verzichtDao.insertVerzicht(verzicht) }.start()
    }

    fun deleteVerzicht(verzicht: Verzicht) {
        Thread { verzichtDao.deleteVerzicht(verzicht) }.start()
    }

    fun findAllVerzichteTest(): List<Verzicht> {
        return verzichtDao.getAllTest()
    }
}
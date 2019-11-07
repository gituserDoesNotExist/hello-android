package com.example.helloandroid

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.persistence.ConstraintViolationException
import com.example.helloandroid.persistence.Verzicht
import com.example.helloandroid.persistence.VerzichtDao
import java.util.*


class VerzichtService(verzichtDao: VerzichtDao) : ViewModel() {

    companion object {
        private const val ERROR_VERZICHT_ALREADY_EXISTS = "Verzicht with name %s already exists!"
    }

    private var verzichtDao = verzichtDao

    fun increaseDaysWithoutChocolateByOneDay(verzichtId: Long) {
        val verzichtFromDb = findVerzichtById(verzichtId)
        verzichtFromDb.ifPresent { verzicht ->
            val days = verzicht.days + 1
            verzicht.days = days
            verzichtDao.updateVerzicht(verzicht)
        }
    }

    fun findByVerzichtNameLiveData(name: String): LiveData<Verzicht> {
        return verzichtDao.findByNameLiveData(name)
    }

    fun findByVerzichtName(name: String): Optional<Verzicht> {
        return Optional.ofNullable(verzichtDao.findByName(name))
    }

    fun findVerzichtById(id: Long): Optional<Verzicht> {
        return Optional.ofNullable(verzichtDao.findById(id))
    }

    fun addNewVerzicht(verzicht: Verzicht) {
        findByVerzichtName(verzicht.verzichtName).ifPresent { throw exceptionVerzichtAlreadyExists(verzicht) }
        verzichtDao.insertVerzicht(verzicht)
    }

    private fun exceptionVerzichtAlreadyExists(verzicht: Verzicht): ConstraintViolationException {
        return ConstraintViolationException(String.format(ERROR_VERZICHT_ALREADY_EXISTS, verzicht.verzichtName))
    }

    fun findAllVerzichte(): List<Verzicht> {
        return verzichtDao.findAll()
    }

    fun findAllVerzichteLiveData(): LiveData<List<Verzicht>> {
        return verzichtDao.findAllVerzichte()
    }


}
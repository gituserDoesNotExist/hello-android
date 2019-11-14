package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations

class FinancesRepository constructor(postenDao: PostenDao, ausgabeDao: AusgabeDao) {

    private val postenDao = postenDao
    private val ausgabeDao = ausgabeDao


    fun findPostenById(id: Long): Posten {
        val posten = postenDao.getById(id)
        val ausgaben = ausgabeDao.getAusgabenByPostenId(id)
        setAusgabenInPosten(posten, ausgaben)

        return posten
    }

    fun findAusgabenForPosten(postenId: Long): List<Ausgabe> {
        return ausgabeDao.getAusgabenByPostenId(postenId)
    }

    fun findAllAusgaben(): List<Ausgabe> {
        return ausgabeDao.getAll()
    }

    fun findAllPosten(): LiveData<List<Posten>> {
        return Transformations.map(postenDao.getAllLiveData()) { allPosten ->
            allPosten.forEach { setAusgabenInPosten(it, ausgabeDao.getAusgabenByPostenId(it.id)) }
            allPosten
        }
    }


    private fun setAusgabenInPosten(posten: Posten, ausgaben: List<Ausgabe>) {
        posten.ausgaben.clear()
        posten.ausgaben.addAll(ausgaben)
    }

    fun insertPosten(posten: Posten): Long {
        val postenId = postenDao.insertPosten(posten)
        val ausgaben = posten.ausgaben
        ausgaben.stream().forEach { it.postenId = postenId }
        ausgabeDao.insertAusgaben(ausgaben)

        return postenId
    }

}
package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.helloandroid.InvalidEntityException

class FinancesRepository constructor(postenDao: PostenDao, ausgabeDao: AusgabeDao) {

    private val postenDao = postenDao
    private val ausgabeDao = ausgabeDao

    fun saveAusgabeForPosten(ausgabe: AusgabeEntity, postenId: Long) {
        if (postenId == 0L) throw InvalidEntityException("Die ID des aktuellen Postens darf icht null sein")
        ausgabe.postenId = postenId
        Thread(Runnable { ausgabeDao.insertAusgabe(ausgabe) }).start()
    }


    fun findPostenById(id: Long): PostenEntity {
        val posten = postenDao.getById(id)
        val ausgaben = ausgabeDao.getAusgabenByPostenId(id)
        setAusgabenInPosten(posten, ausgaben)

        return posten
    }

    fun findAusgabenForPosten(postenId: Long): List<AusgabeEntity> {
        return ausgabeDao.getAusgabenByPostenId(postenId)
    }

    fun findAllAusgaben(): List<AusgabeEntity> {
        return ausgabeDao.getAll()
    }

    fun findAllPosten(): LiveData<List<PostenEntity>> {
        return Transformations.map(postenDao.getAllLiveData()) { allPosten ->
            allPosten.forEach { setAusgabenInPosten(it, ausgabeDao.getAusgabenByPostenId(it.id)) }
            allPosten
        }
    }


    private fun setAusgabenInPosten(posten: PostenEntity, ausgaben: List<AusgabeEntity>) {
        posten.ausgaben.clear()
        posten.ausgaben.addAll(ausgaben)
    }

    fun insertPosten(posten: PostenEntity): Long {
        val postenId = postenDao.insertPosten(posten)
        val ausgaben = posten.ausgaben
        ausgaben.stream().forEach { it.postenId = postenId }
        ausgabeDao.insertAusgaben(ausgaben)

        return postenId
    }

}
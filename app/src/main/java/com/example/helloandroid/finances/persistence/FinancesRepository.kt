package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.helloandroid.InvalidEntityException
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.Posten
import java.util.stream.Collectors

class FinancesRepository(postenDao: PostenDao, ausgabeDao: AusgabeDao, postenWithAusgabenDao: PostenWithAusgabenDao) {

    private val postenDao = postenDao
    private val ausgabeDao = ausgabeDao
    private val postenWithAusgabeDao = postenWithAusgabenDao
    private val postenWithAusgabeMapper = PostenWithAusgabenEntityToPostenMapper()
    private val ausgabeEntityToAusgabeMapper = AusgabeEntityToAusgabeMapper()

    fun findAllPosten(): LiveData<List<Posten>> {
        val postenWithAusgaben = postenWithAusgabeDao.getAllPostenWithAusgaben()
        return Transformations.map(postenWithAusgaben) { it.stream().map(this::asPosten).collect(Collectors.toList()) }
    }

    private fun asPosten(it: PostenWithAusgabenEntity) = postenWithAusgabeMapper.fromPostenWithAusgabenToPosten(it)


    fun saveAusgabeForPosten(ausgabe: AusgabeEntity, postenId: Long) {
        if (postenId == 0L) throw InvalidEntityException("Die ID des aktuellen Postens darf icht null sein")
        ausgabe.postenId = postenId
        Thread(Runnable { ausgabeDao.insertAusgabe(ausgabe) }).start()
    }

    fun findPostenById(id: Long): PostenEntity {
        val posten = postenDao.getById(id)
        val ausgaben = ausgabeDao.getAusgabenByPostenId(id)

        return posten
    }

    fun findAusgabenForPosten(postenId: Long): LiveData<List<Ausgabe>> {
        return Transformations.map(ausgabeDao.getAusgabenByPostenId(postenId)) { ausgabenEntities ->
            ausgabenEntities.stream().map(ausgabeEntityToAusgabeMapper::asAusgabe).collect(Collectors.toList())
        }
    }

    fun findAllAusgaben(): List<Ausgabe> {
        return ausgabeDao.getAll().stream().map(ausgabeEntityToAusgabeMapper::asAusgabe).collect(Collectors.toList())
    }


    fun insertPosten(posten: PostenEntity): Long {
        val postenId = postenDao.insertPosten(posten)

        return postenId
    }

}
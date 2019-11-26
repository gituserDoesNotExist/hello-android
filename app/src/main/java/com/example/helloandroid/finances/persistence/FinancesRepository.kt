package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.helloandroid.DatabaseAsyncTask
import com.example.helloandroid.InvalidEntityException
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.Posten
import com.example.helloandroid.finances.PostenStub
import java.util.stream.Collectors

class FinancesRepository(private val postenDao: PostenDao, private val ausgabeDao: AusgabeDao,
                         private val postenWithAusgabenDao: PostenWithAusgabenDao) {

    private val ausgabeEntityToAusgabeMapper = AusgabeEntityToAusgabeMapper()
    private val postenWithAusgabeEntityToPostenMapper = PostenWithAusgabenEntityToPostenMapper()
    private val postenStubMapper = PostenStubMapper()
    private val postenEntityToPostenMapper: PostenEntityToPostenMapper = PostenEntityToPostenMapper()

    fun findPostenById(postenId: Long): LiveData<Posten> {
        return Transformations.map(postenWithAusgabenDao.getPostenById(postenId)) {
            postenWithAusgabeEntityToPostenMapper.fromPostenWithAusgabenToPosten(it)
        }
    }

    fun saveAusgabeForPosten(ausgabe: Ausgabe, postenId: Long) {
        if (postenId == 0L) throw InvalidEntityException("Die ID des aktuellen Postens darf icht null sein")
        ausgabe.postenId = postenId
        val ausgabeEntity = ausgabeEntityToAusgabeMapper.asAusgabeEntity(ausgabe)
        DatabaseAsyncTask(this::upsertAusgabe).execute(ausgabeEntity)
    }

    private fun upsertAusgabe(ausgabe: AusgabeEntity) {
        if (ausgabeDao.doesAusgabeExist(ausgabe.id)) {
            ausgabeDao.updateAusgabe(ausgabe)
        } else
            ausgabeDao.insertAusgabe(ausgabe)
    }

    fun deleteAusgabe(ausgabe: Ausgabe) {
        DatabaseAsyncTask(ausgabeDao::deleteAusgabe).execute(ausgabe.id)
    }

    fun findPostenStubs(): LiveData<List<PostenStub>> {
        return Transformations.map(postenWithAusgabenDao.getPostenStubs()) { stubs ->
            stubs.stream().map(postenStubMapper::asPostenStub).collect(Collectors.toList())
        }
    }


    fun savePosten(posten: Posten) {
        val postenEntity = postenEntityToPostenMapper.asPostenEntity(posten)
        Thread(Runnable { postenDao.insertPosten(postenEntity) }).start()
    }

    fun deletePosten(postenId: Long) {
        DatabaseAsyncTask<Long, Unit>({
            ausgabeDao.deleteAusgabenWithPostenId(postenId)
            postenDao.deletePosten(postenId)
        }).execute(postenId)
    }

}
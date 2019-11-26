package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
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

    fun findPostenById(postenId: Long): LiveData<Posten> {
        return Transformations.map(postenWithAusgabenDao.getPostenById(postenId)) {
            postenWithAusgabeEntityToPostenMapper.fromPostenWithAusgabenToPosten(it)
        }
    }

    fun saveAusgabeForPosten(ausgabe: Ausgabe, postenId: Long) {
        if (postenId == 0L) throw InvalidEntityException("Die ID des aktuellen Postens darf icht null sein")
        ausgabe.postenId = postenId
        Thread(Runnable { ausgabeDao.insertAusgabe(ausgabeEntityToAusgabeMapper.asAusgabeEntity(ausgabe)) }).start()
    }

    fun findPostenStubs(): LiveData<List<PostenStub>> {
        return Transformations.map(postenWithAusgabenDao.getPostenStubs()) { stubs ->
            stubs.stream().map(postenStubMapper::asPostenStub).collect(Collectors.toList())
        }
    }

    fun deletePosten(postenId: Long) {
        Thread(Runnable {
            ausgabeDao.deleteAusgabenWithPostenId(postenId)
            postenDao.deletePosten(postenId)
        }).start()
    }

}
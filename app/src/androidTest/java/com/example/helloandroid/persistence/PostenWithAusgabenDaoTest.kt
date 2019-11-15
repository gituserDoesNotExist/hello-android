package com.example.helloandroid.persistence

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.helloandroid.finances.persistence.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
class PostenWithAusgabenDaoTest {

    private lateinit var testCandidate: PostenWithAusgabenDao
    private lateinit var postenDao: PostenDao
    private lateinit var ausgabeDao: AusgabeDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        val database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        postenDao = database.postenDao()
        ausgabeDao = database.ausgabeDao()
        testCandidate = database.postenWithAusgabeDao()
    }

    @Test
    fun getById() {
        val posten = PostenEntity("Lebensmittel")
        val postenId = postenDao.insertPosten(posten)

        assertThat(postenId).isNotEqualTo(0)

        val ausgabe = AusgabeEntity(BigDecimal.ONE, "-", LocalDateTime.now())
        ausgabe.postenId = postenId
        val otherAusgabe = AusgabeEntity(BigDecimal.TEN, "-", LocalDateTime.now())
        otherAusgabe.postenId = postenId
        val ausgaben = listOf(ausgabe, otherAusgabe)
        ausgabeDao.insertAusgaben(ausgaben)

        val result = testCandidate.getById(postenId)

        assertThat(result.ausgaben.size).isEqualTo(2)
        assertThat(result.ausgaben[0].wert).isEqualTo(BigDecimal.ONE)
        assertThat(result.ausgaben[1].wert).isEqualTo(BigDecimal.TEN)
    }


}
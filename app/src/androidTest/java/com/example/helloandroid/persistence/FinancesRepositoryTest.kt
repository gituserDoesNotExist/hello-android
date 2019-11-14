package com.example.helloandroid.persistence

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.helloandroid.finances.persistence.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class FinancesRepositoryTest {

    private lateinit var testCandidate: FinancesRepository
    private lateinit var postenDao: PostenDao
    private lateinit var ausgabeDao: AusgabeDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        val database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        postenDao = database.postenDao()
        ausgabeDao = database.ausgabeDao()
        testCandidate = FinancesRepository(postenDao, ausgabeDao)
    }

    @Test
    fun testFindPostenById() {
        val posten = Posten("Lebensmittel")
        val postenId = postenDao.insertPosten(posten)

        assertThat(postenId).isNotEqualTo(0)

        val ausgabe = Ausgabe(BigDecimal.ONE, "-", LocalDateTime.now())
        ausgabe.postenId = postenId
        val otherAusgabe = Ausgabe(BigDecimal.TEN, "-", LocalDateTime.now())
        otherAusgabe.postenId = postenId
        val ausgaben = listOf(ausgabe, otherAusgabe)
        ausgabeDao.insertAusgaben(ausgaben)

        val result: Posten = testCandidate.findPostenById(postenId)

        assertThat(result.ausgaben[0].wert).isEqualTo(BigDecimal.ONE)
        assertThat(result.ausgaben[1].wert).isEqualTo(BigDecimal.TEN)
    }

    @Test
    fun testInsertPosten() {
        val posten = Posten("Lebensmittel")
        val ausgabe = Ausgabe(BigDecimal.ONE, "-", LocalDateTime.now())
        val otherAusgabe = Ausgabe(BigDecimal.TEN, "-", LocalDateTime.now())
        posten.ausgaben.addAll(listOf(ausgabe, otherAusgabe))

        val postenId = testCandidate.insertPosten(posten)

        assertThat(postenId).isNotEqualTo(0)

        val postenResult = postenDao.getById(postenId)
        val ausgabenResult = ausgabeDao.getAusgabenByPostenId(postenId)

        assertThat(postenResult.name).isEqualTo("Lebensmittel")
        assertThat(ausgabenResult.size).isEqualTo(2)
    }

}
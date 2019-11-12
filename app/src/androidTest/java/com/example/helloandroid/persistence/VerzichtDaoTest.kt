package com.example.helloandroid.persistence

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.assertj.core.api.Assertions.*
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime


@RunWith(AndroidJUnit4::class)
class VerzichtDaoTest {

    private lateinit var verzichtDao: VerzichtDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        verzichtDao = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build().verzichtDao()
    }

    @Test
    fun testInsertVerzicht_IdSet_KeepsId() {
        val verzicht = Verzicht(VERZICHT_NAME, 0)
        verzicht.id = VERZICHT_ID
        val id = verzichtDao.insertVerzicht(verzicht)

        assertThat(id).isEqualTo(VERZICHT_ID)

        val recentlyInsertedVerzicht = verzichtDao.findById(id)
        assertThat(recentlyInsertedVerzicht.timestampDayAdded).isEqualTo(TIMESTAMP_TODAY)
    }

    @Test
    fun testInsertVerzicht_NoIdGiven_GeneratesId() {
        val id = verzichtDao.insertVerzicht(Verzicht(VERZICHT_NAME, 0))

        assertThat(id).isNotNull()
        assertThat(verzichtDao.findById(id).timestampDayAdded).isEqualTo(TIMESTAMP_TODAY)
    }

    @Test
    fun testInsertVerzicht_VerzichtWithIdAlreadyExists_ThrowsException() {
        val verzicht = Verzicht(VERZICHT_NAME, 0)
        verzicht.id = VERZICHT_ID
        verzichtDao.insertVerzicht(verzicht)

        val findAll = verzichtDao.findAll()

        assertThat(findAll.size, equalTo(1))

        assertThatThrownBy { verzichtDao.insertVerzicht(verzicht) }//
         .isInstanceOf(SQLiteConstraintException::class.java)
    }

    @Test
    fun testUpdateVerzicht_SameId_UpdatesEntity() {
        val verzicht = Verzicht(VERZICHT_NAME, 0)
        verzicht.id = VERZICHT_ID
        verzichtDao.insertVerzicht(verzicht)

        val verzichtFromDb = verzichtDao.findById(VERZICHT_ID)

        verzichtFromDb.days = 14

        verzichtDao.updateVerzicht(verzichtFromDb)

        val updatedVerzicht = verzichtDao.findById(VERZICHT_ID)

        assertThat(updatedVerzicht.days).isEqualTo(14)
    }

    @Test
    fun testFindById() {
        verzichtDao.insertVerzicht(Verzicht(VERZICHT_NAME, 0))

        val verzichte = verzichtDao.findAll()

        assertThat(verzichte.size).isEqualTo(1)

        val id = verzichte[0].id

        val result = verzichtDao.findById(id)

        assertThat(result.verzichtName).isEqualTo(VERZICHT_NAME)
        assertThat(result.timestampDayAdded).isEqualTo(TIMESTAMP_TODAY)
    }

    @Test
    fun testFindByLebensmittelType() {
        verzichtDao.insertVerzicht(Verzicht(VERZICHT_NAME, 0))

        val result = verzichtDao.findByName(VERZICHT_NAME)

        assertThat(result.timestampDayAdded).isEqualTo(TIMESTAMP_TODAY)
    }


    companion object {
        private const val VERZICHT_ID = 10L
        private val TIMESTAMP_TODAY = LocalDateTime.now()
        private const val VERZICHT_NAME = "Chocolate"
    }

}

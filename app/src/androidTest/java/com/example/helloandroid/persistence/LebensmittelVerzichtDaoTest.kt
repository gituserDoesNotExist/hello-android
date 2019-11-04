package com.example.helloandroid.persistence

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime


@RunWith(AndroidJUnit4::class)
class LebensmittelVerzichtDaoTest {

    private lateinit var verzichtDao: LebensmittelVerzichtDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        verzichtDao = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build().lebensmittelVerzichtDao()
    }

    @Test
    fun testInsertVerzicht() {
        verzichtDao.insertLebensmittelVerzicht(LebensmittelVerzicht(CHOCOLATE_ID,LebensmittelType.CHOCOLATE, TIMESTAMP_TODAY))

        val findAll = verzichtDao.findAll()

        assertThat(findAll.size, equalTo(1))
        assertThat(findAll[0].id, equalTo(CHOCOLATE_ID))
        assertThat(findAll[0].timestampDayAdded, equalTo(TIMESTAMP_TODAY))
    }

    @Test
    fun testFindByLebensmittelType() {
        verzichtDao.insertLebensmittelVerzicht(LebensmittelVerzicht(CHOCOLATE_ID,LebensmittelType.CHOCOLATE, TIMESTAMP_TODAY))

        val chocolateVerzicht = verzichtDao.findByLebensmittelType(LebensmittelType.CHOCOLATE)

        assertThat(chocolateVerzicht.id, equalTo(CHOCOLATE_ID))
        assertThat(chocolateVerzicht.timestampDayAdded, equalTo(TIMESTAMP_TODAY))
    }


    companion object {
        private const val CHOCOLATE_ID = 10
        private val TIMESTAMP_TODAY = LocalDateTime.now()
    }

}

package com.example.helloandroid.timerecording.persistence

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.helloandroid.persistence.AppDatabase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalendarConfigurationDaoTest {

    private lateinit var testCandidate: CalendarConfigurationDao

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getContext()
        val database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        testCandidate = database.calendarConfigurationDao()
    }

    @Test
    fun testSaveConfiguration() {
        val config = CalendarConfigurationEntity().apply {
            this.taetigkeiten = listOf("a", "b", "c")
            this.teilnehmer = listOf("d", "e", "f")
        }

        testCandidate.insertConfiguration(config)
    }

}
package com.example.helloandroid.persistence

import android.app.Instrumentation
import android.util.Log
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


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
        verzichtDao.insertLebensmittelVerzicht(LebensmittelVerzicht(1,"chocolate","timestamp"))

        val findAll = verzichtDao.findAll()

        assertThat(findAll.size,IsEqual.equalTo(1))
    }

}

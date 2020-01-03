package com.example.helloandroid.persistence

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.helloandroid.finances.persistence.AusgabeDao
import com.example.helloandroid.finances.persistence.PostenDao
import com.example.helloandroid.finances.persistence.PostenWithAusgabenDao
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostenWithAusgabenContainerDaoTest {

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




}
package com.example.helloandroid.persistence

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    @Test
    fun testGetDb_ReturnsAlwaysSameInstance() {
        val applicationContext = InstrumentationRegistry.getContext()
        val db = AppDatabase.getDb(applicationContext)

        println("printing db")
        println(db)
        println(db)
        println(db)
        println(db)
    }
}
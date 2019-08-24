package com.example.helloandroid.persistence

import android.content.Context
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val targetContext: Context = InstrumentationRegistry.getTargetContext()

        val db: AppDatabase = AppDatabase.getDb(targetContext)

        println(db)
    }
}
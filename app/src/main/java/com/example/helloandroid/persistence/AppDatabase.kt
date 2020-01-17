package com.example.helloandroid.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.helloandroid.finances.persistence.*
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationDao
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationEntity
import com.example.helloandroid.verzicht.persistence.Verzicht
import com.example.helloandroid.verzicht.persistence.VerzichtDao

@Database(entities = [Verzicht::class, PostenEntity::class, AusgabeEntity::class, CalendarConfigurationEntity::class],
    version = 1)
@TypeConverters(OptionalLocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun verzichtDao(): VerzichtDao

    abstract fun postenDao(): PostenDao

    abstract fun ausgabeDao(): AusgabeDao

    abstract fun postenWithAusgabeDao(): PostenWithAusgabenDao

    abstract fun calendarConfigurationDao(): CalendarConfigurationDao

    companion object {
        private const val DATABASE_NAME = "APP_DATABASE"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDb(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            val instance = Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
            INSTANCE = instance
            return instance
        }

    }
}
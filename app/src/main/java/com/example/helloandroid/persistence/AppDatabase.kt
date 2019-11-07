package com.example.helloandroid.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Verzicht::class], version = 1)
@TypeConverters(HelloTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun verzichtDao(): VerzichtDao

    companion object {
        private const val DATABASE_NAME = "APP_DATABASE"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
            val tmpInstance = INSTANCE
            if (tmpInstance != null) {
                return tmpInstance
            }
            synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries()
                        .build()
                INSTANCE = instance
                return instance
            }
        }
    }


}
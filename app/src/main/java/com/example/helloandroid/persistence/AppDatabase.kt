package com.example.helloandroid.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LebensmittelVerzicht::class),version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lebensmittelVerzichtDao(): LebensmittelVerzichtDao

    companion object {
        private const val DATABASE_NAME = "APP_DATABASE"
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDb(context:Context): AppDatabase {
            val tmpInstance = INSTANCE
            if (tmpInstance != null) {
                return tmpInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,AppDatabase::class.java,DATABASE_NAME).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}
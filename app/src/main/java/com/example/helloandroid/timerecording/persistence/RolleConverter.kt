package com.example.helloandroid.timerecording.persistence

import androidx.room.TypeConverter
import com.example.helloandroid.timerecording.Rolle

class RolleConverter {

    @TypeConverter
    fun fromRolle(rolle: Rolle): Long {
        return rolle.id
    }

    @TypeConverter
    fun toRolle(rolleId: Long): Rolle {
        return Rolle.fromId(rolleId)
    }


}
package com.example.helloandroid.timerecording.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.helloandroid.persistence.StringListTypeConverter

@Entity(tableName = "CALENDER_CONFIGURATION_ENTITY")
class CalendarConfigurationEntity {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    var id: Long = 1

    var appUser: String = ""

    @TypeConverters(StringListTypeConverter::class)
    lateinit var kategorien: List<String>

    @TypeConverters(StringListTypeConverter::class)
    lateinit var teilnehmer: List<String>

    @TypeConverters(StringListTypeConverter::class)
    lateinit var fahrzeuge: List<String>

    @TypeConverters(StringListTypeConverter::class)
    lateinit var maschinen: List<String>


}
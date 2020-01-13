package com.example.helloandroid.timerecording.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.helloandroid.persistence.*
import com.example.helloandroid.timerecording.config.*

@Entity(tableName = "CALENDER_CONFIGURATION_ENTITY")
class CalendarConfigurationEntity {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = false)
    var id: Long = 1

    @TypeConverters(PersonTypeConverter::class)
    var appUser: Person = Person()

    @TypeConverters(ProduktListTypeConverter::class)
    lateinit var produkte: List<Produkt>

    @TypeConverters(TaetigkeitListTypeConverter::class)
    lateinit var taetigkeiten: List<Taetigkeit>

    @TypeConverters(PersonListTypeConverter::class)
    lateinit var teilnehmer: List<Person>

    @TypeConverters(FahrzeugListTypeConverter::class)
    lateinit var fahrzeuge: List<Fahrzeug>

    @TypeConverters(AnbaugeraetListTypeConverter::class)
    lateinit var anbaugeraete: List<Anbaugeraet>


}
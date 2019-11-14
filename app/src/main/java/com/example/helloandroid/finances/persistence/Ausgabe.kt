package com.example.helloandroid.finances.persistence

import androidx.room.*
import com.example.helloandroid.persistence.BaseEntity
import com.example.helloandroid.persistence.BigDecimalConverter
import com.example.helloandroid.persistence.LocalDateTimeConverter
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(
    tableName = "AUSGABE", foreignKeys = [ForeignKey(
        entity = Posten::class, parentColumns = arrayOf("ID"), childColumns = arrayOf("POSTEN_ID")
    )]
)
class Ausgabe(wert: BigDecimal, beschreibung: String, datum: LocalDateTime) : BaseEntity() {

    @ColumnInfo(name = "WERT")
    @TypeConverters(BigDecimalConverter::class)
    val wert: BigDecimal = wert

    @ColumnInfo(name = "DATUM")
    @TypeConverters(LocalDateTimeConverter::class)
    val datum: LocalDateTime = datum

    @ColumnInfo(name="BESCHREIBUNG")
    val beschreibung = beschreibung

    @ColumnInfo(name = "POSTEN_ID")
    var postenId: Long = 0

}

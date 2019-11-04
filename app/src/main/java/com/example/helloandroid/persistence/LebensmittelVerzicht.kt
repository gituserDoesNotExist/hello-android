package com.example.helloandroid.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "LEBENSMITTEL_VERZICHT")
data class LebensmittelVerzicht(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="name") val name: LebensmittelType,
    @ColumnInfo(name="TIMESTAMP_DAY_ADDED") val timestampDayAdded: LocalDateTime
)
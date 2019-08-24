package com.example.helloandroid.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LEBENSMITTEL_VERZICHT")
data class LebensmittelVerzicht(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="name") val name: String
)
package com.example.helloandroid.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import java.time.LocalDateTime

@Entity(tableName = "VERZICHT", indices = [Index(value = ["VERZICHT_NAME"], unique = true)])
class Verzicht(verzichtName: String, days: Int, timestampDayAdded: LocalDateTime) : BaseEntity() {

    @ColumnInfo(name = "VERZICHT_NAME")
    val verzichtName: String = verzichtName
    @ColumnInfo(name ="DAYS")
    var days: Int = days
    @ColumnInfo(name = "TIMESTAMP_DAY_ADDED")
    val timestampDayAdded: LocalDateTime = timestampDayAdded

}
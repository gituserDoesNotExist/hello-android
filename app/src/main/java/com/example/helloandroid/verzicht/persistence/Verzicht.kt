package com.example.helloandroid.verzicht.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.example.helloandroid.persistence.BaseEntity
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "VERZICHT", indices = [Index(value = ["VERZICHT_NAME"], unique = true)])
class Verzicht(verzichtName: String, days: Int) : BaseEntity() {

    @ColumnInfo(name = "VERZICHT_NAME")
    val verzichtName: String = verzichtName
    @ColumnInfo(name ="DAYS")
    var days: Int = days
    @ColumnInfo(name = "TIMESTAMP_DAY_ADDED")
    var timestampDayAdded: Optional<LocalDateTime> = Optional.empty()

}
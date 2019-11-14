package com.example.helloandroid.verzicht.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.TypeConverters
import com.example.helloandroid.persistence.BaseEntity
import com.example.helloandroid.persistence.OptionalLocalDateTimeConverter
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "VERZICHT", indices = [Index(value = ["VERZICHT_NAME"], unique = true)])
class Verzicht(verzichtName: String) : BaseEntity() {

    @ColumnInfo(name = "VERZICHT_NAME")
    val verzichtName: String = verzichtName
    @ColumnInfo(name ="DAYS")
    var days: Int = 0
    @ColumnInfo(name = "TIMESTAMP_DAY_ADDED")
    @TypeConverters(OptionalLocalDateTimeConverter::class)
    var timestampDayAdded: Optional<LocalDateTime> = Optional.empty()

}
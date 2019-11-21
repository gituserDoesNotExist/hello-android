package com.example.helloandroid.finances.persistence

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.example.helloandroid.persistence.BaseEntity
import com.example.helloandroid.persistence.BigDecimalConverter
import com.example.helloandroid.persistence.LocalDateTimeConverter
import java.math.BigDecimal
import java.time.LocalDateTime


class PostenStubEntity(id: Long, name: String, gesamtausgaben: BigDecimal) {

    val id = id

    val name = name

    @TypeConverters(BigDecimalConverter::class)
    val gesamtausgaben: BigDecimal = gesamtausgaben

}
package com.example.helloandroid.finances.persistence

import androidx.room.TypeConverters
import com.example.helloandroid.persistence.BigDecimalConverter
import java.math.BigDecimal


class PostenStubEntity(id: Long, name: String, gesamtausgaben: BigDecimal) {

    val id = id

    val name = name

    @TypeConverters(BigDecimalConverter::class)
    val gesamtausgaben: BigDecimal = gesamtausgaben

}
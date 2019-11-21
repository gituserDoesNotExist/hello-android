package com.example.helloandroid.persistence

import androidx.room.TypeConverter
import java.math.BigDecimal

class BigDecimalConverter {

    @TypeConverter
    fun fromBigDecimal(value: BigDecimal): String {
        return value.toString()
    }

    @TypeConverter
    fun toBigDecimal(value: String?): BigDecimal {
        return value?.let { BigDecimal(it) } ?: BigDecimal.ZERO
    }


}
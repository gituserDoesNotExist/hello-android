package com.example.helloandroid.timerecording

import com.example.helloandroid.HelloBigDecimalFormat
import java.math.BigDecimal

class Arbeitszeit(decimalHours: BigDecimal) {

    constructor(value: String) : this(BigDecimal(value))

    var dauer: BigDecimal = HelloBigDecimalFormat.default(decimalHours)

    fun getTimeInMinutes(): Long {
        return dauer.times(BigDecimal(60)).longValueExact()
    }

    override fun toString(): String {
        return String.format("%.1f Stunden",dauer)
    }
}
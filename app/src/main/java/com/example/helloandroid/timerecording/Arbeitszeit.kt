package com.example.helloandroid.timerecording

import java.math.BigDecimal
import java.math.RoundingMode

class Arbeitszeit(decimalHours: BigDecimal) {

    var dauer: BigDecimal = decimalHours.setScale(1, RoundingMode.DOWN)

    fun getTimeInMinutes(): Long {
        return dauer.times(BigDecimal(60)).longValueExact()
    }

    override fun toString(): String {
        return String.format("%.1f Stunden",dauer)
    }
}
package com.example.helloandroid.timerecording

import java.math.BigDecimal
import java.math.RoundingMode

class Arbeitszeit(decimalHours: BigDecimal) {

    var minutes = 0L
        private set


    init {
        minutes = decimalHours.setScale(1,RoundingMode.DOWN).times(BigDecimal(60)).longValueExact()
    }

}
package com.example.helloandroid

import java.math.BigDecimal
import java.math.RoundingMode

object HelloBigDecimalFormat {

    fun default(value: BigDecimal) : BigDecimal {
        return value.setScale(1, RoundingMode.DOWN)
    }

}
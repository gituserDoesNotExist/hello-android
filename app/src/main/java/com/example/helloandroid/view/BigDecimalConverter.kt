package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.apache.commons.lang3.StringUtils
import java.math.BigDecimal

object BigDecimalConverter {

    @JvmStatic
    @InverseMethod("stringToBigDecimal")
    fun bigDecimalToString(value: BigDecimal?): String {
        return if (BigDecimal.ZERO == value) "" else value.toString()
    }

    @JvmStatic
    fun stringToBigDecimal(value: String): BigDecimal {
        return if (StringUtils.isBlank(value)) BigDecimal.ZERO else BigDecimal(value)
    }

}
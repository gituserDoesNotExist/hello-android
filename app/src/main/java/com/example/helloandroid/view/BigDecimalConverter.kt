package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.apache.commons.lang3.StringUtils
import java.math.BigDecimal
import java.text.DecimalFormat

object BigDecimalConverter {

    @JvmStatic
    @InverseMethod("stringToBigDecimal")
    fun bigDecimalToString(value: BigDecimal?): String {
        val decimalFormat = DecimalFormat().apply {
            this.maximumFractionDigits = 2
        }
        return decimalFormat.format(value)
    }

    @JvmStatic
    fun stringToBigDecimal(value: String): BigDecimal {
       return if (StringUtils.isBlank(value) || value.contains(",")) BigDecimal.ZERO else BigDecimal(value)
    }

}
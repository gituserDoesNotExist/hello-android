package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import com.example.helloandroid.HelloBigDecimalFormat
import org.apache.commons.lang3.StringUtils
import java.math.BigDecimal

object BigDecimalConverter {

    @JvmStatic
    @InverseMethod("stringToBigDecimal")
    fun bigDecimalToString(value: BigDecimal?): String {
        if (BigDecimal.ZERO == value || HelloBigDecimalFormat.default(BigDecimal.ZERO) == value) {
            return "0"
        }
        return value.toString()
    }

    @JvmStatic
    fun stringToBigDecimal(value: String): BigDecimal {
        return if (StringUtils.isBlank(value) || value.contains(",")) {
            BigDecimal.ZERO
        } else {
            HelloBigDecimalFormat.default(BigDecimal(value))
        }
    }

}
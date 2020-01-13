package com.example.helloandroid.view

import androidx.databinding.InverseMethod
import org.apache.commons.lang3.StringUtils

object IntConverter {

    @JvmStatic
    @InverseMethod("stringToInt")
    fun intToString(value: Int): String {
        return value.toString()
    }

    @JvmStatic
    fun stringToInt(value: String): Int {
        return if (StringUtils.isBlank(value) || value.contains(",")) {
            0
        } else {
            value.toInt()
        }
    }

}
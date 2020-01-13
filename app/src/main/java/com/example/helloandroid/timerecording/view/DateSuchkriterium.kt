package com.example.helloandroid.timerecording.view

import com.example.helloandroid.view.LocalDateConverter
import org.threeten.bp.LocalDate

class DateSuchkriterium constructor(private val date: LocalDate) : Suchkriterium<LocalDate> {

    override fun anzeigename(): String {
        return LocalDateConverter.dateToString(date)
    }

    fun getSuchkriterium(): LocalDate {
        return date
    }

}
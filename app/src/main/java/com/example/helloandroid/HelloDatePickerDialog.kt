package com.example.helloandroid

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import org.threeten.bp.LocalDate

class HelloDatePickerDialog(context: Context, dateSetListener: (LocalDate) -> Unit, preselectedDate: LocalDate) :
    DatePickerDialog(context) {

    init {
        val listener = DatePicker.OnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            dateSetListener(LocalDate.of(year, monthOfYear + 1, dayOfMonth))
        }
        datePicker.init(preselectedDate.year, preselectedDate.monthValue - 1, preselectedDate.dayOfMonth, listener)
    }


}
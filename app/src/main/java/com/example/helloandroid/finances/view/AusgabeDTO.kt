package com.example.helloandroid.finances.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalTime

class AusgabeDTO : BaseObservable() {

    var datum: LocalDate = LocalDate.now()
        @Bindable get(): LocalDate = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.datum)
            }
        }

    var uhrzeit: LocalTime = LocalTime.now()
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.uhrzeit)
            }
        }

    var wert: BigDecimal = BigDecimal.ZERO
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.wert)
            }
        }
    var beschreibung: String = ""
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.beschreibung)
            }
        }
}
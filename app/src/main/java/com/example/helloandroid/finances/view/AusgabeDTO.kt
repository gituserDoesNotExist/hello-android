package com.example.helloandroid.finances.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.helloandroid.BR
import java.time.LocalDate
import java.time.LocalTime

class AusgabeDTO : BaseObservable() {

    var datum: LocalDate = LocalDate.now()
        @Bindable get(): LocalDate = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.addAusgabeDialog)
            }
        }

    var uhrzeit: LocalTime = LocalTime.now()
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.addAusgabeDialog)
            }
        }

    var wert: String = ""
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.addAusgabeDialog)
            }
        }
    var beschreibung: String = ""
        @Bindable get
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.addAusgabeDialog)
            }
        }
}
package com.example.helloandroid.timerecording.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import org.threeten.bp.LocalDate

class ArbeitsverhaeltnisUebersichtDTO(startDate: LocalDate, endDate: LocalDate) : BaseObservable() {

    var startDate: LocalDate = startDate
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.startDate)
            }
        }

    var endDate: LocalDate = endDate
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.endDate)
            }
        }

}
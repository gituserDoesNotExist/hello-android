package com.example.helloandroid.verzicht.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class VerzichtDTO : BaseObservable() {

    var name: String = ""
        @Bindable get(): String = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.name)
            }
        }

    var days: String = ""
        @Bindable get(): String = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.days)
            }
        }

    var daysIncreasedToday = false
        @Bindable get(): Boolean = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.daysIncreasedToday)
            }
        }

}
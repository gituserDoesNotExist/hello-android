package com.example.helloandroid.timerecording.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ArbeitsverhaeltnisDetailsDTO : BaseObservable() {


    var datum: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.datum)
            }
        }


    var leistungsnehmer: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.leistungsnehmer)
            }
        }

    var leistungserbringer: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.leistungserbringer)
            }
        }

    var kategorie: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.kategorie)
            }
        }

    var kommentar: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.kommentar)
            }
        }


}
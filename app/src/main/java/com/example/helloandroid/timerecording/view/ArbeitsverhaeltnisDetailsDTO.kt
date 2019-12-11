package com.example.helloandroid.timerecording.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import java.math.BigDecimal

class ArbeitsverhaeltnisDetailsDTO : BaseObservable() {

    var erstelltAm: LocalDateTime
        get() = _erstelltAm ?: throw UninitializedPropertyAccessException("Field was queried before being initialized")
        set(value) {
            _erstelltAm = value
        }

    private var _erstelltAm: LocalDateTime? = null
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.datum)
            }
        }

    var erstelltVon: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.datum)
            }
        }

    var datum: LocalDate
        get() = _datum ?: throw UninitializedPropertyAccessException("Field was queried before being initialized")
        set(value) {
            _datum = value
        }

    private var _datum: LocalDate? = null
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.datum)
            }
        }

    var dauer: BigDecimal = BigDecimal.ZERO
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.dauer)
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
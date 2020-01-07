package com.example.helloandroid.timerecording.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.helloandroid.ZoneIds
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDate.now
import org.threeten.bp.ZoneId
import java.math.BigDecimal

class ArbeitsverhaeltnisDTO : BaseObservable() {

    var datumZeiterfassung: LocalDate = now(ZoneId.of(ZoneIds.EUROPE_BERLIN.zoneId))
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.datumZeiterfassung)
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


    var leistungserbringer: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.leistungserbringer)
            }
        }


    var fahrzeug: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.fahrzeug)
            }
        }


    var maschine: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.maschine)
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

    var dauer: BigDecimal = BigDecimal.ZERO
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.dauer)
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


    fun reset() {
        this.maschine = ""
        this.fahrzeug = ""
        this.leistungserbringer = ""
        this.datumZeiterfassung = now(ZoneId.of(ZoneIds.EUROPE_BERLIN.zoneId))
        this.dauer = BigDecimal.ZERO
        this.kategorie = ""
        this.kommentar = ""
        this.leistungsnehmer = ""
    }

}
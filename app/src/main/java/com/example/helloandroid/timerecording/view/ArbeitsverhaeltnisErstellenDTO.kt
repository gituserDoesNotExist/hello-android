package com.example.helloandroid.timerecording.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.helloandroid.ZoneIds
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDate.now
import org.threeten.bp.ZoneId
import java.math.BigDecimal

class ArbeitsverhaeltnisErstellenDTO : BaseObservable() {

    var datumZeiterfassung: LocalDate = now(ZoneId.of(ZoneIds.EUROPE_BERLIN.zoneId))
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.datum)
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


}
package com.example.helloandroid.finances.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import com.example.helloandroid.HelloZoneID
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.ZoneId
import java.math.BigDecimal

class AusgabeDTO : BaseObservable() {

    var id: Long = 0

    var datum: LocalDate = LocalDate.now()
        @Bindable get(): LocalDate = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.datum)
            }
        }

    var uhrzeit: LocalTime = LocalTime.now(ZoneId.of(HelloZoneID.EUROPE_BERLIN.zoneId))
        @Bindable get(): LocalTime = field
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
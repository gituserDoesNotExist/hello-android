package com.example.helloandroid.timerecording.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import org.threeten.bp.LocalDateTime

class ArbeitsverhaeltnisDetailsDTO : BaseObservable() {

    lateinit var remoteId: String
    lateinit var version: String

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

    var arbeitsverhaeltnisDto: ArbeitsverhaeltnisDTO = ArbeitsverhaeltnisDTO()
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.arbeitsverhaeltnisDto)
            }
        }

}
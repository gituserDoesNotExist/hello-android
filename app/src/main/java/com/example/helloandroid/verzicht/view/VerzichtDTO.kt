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

}
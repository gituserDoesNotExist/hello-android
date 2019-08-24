package com.example.helloandroid

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class ItemViewModel(private val _id: Long, private var _title: String) : BaseObservable() {

    val id: Long
        get() = _id

    var title: String
        @Bindable get() = _title
        set(value) {
            _title = value
            println("setter called")
            notifyPropertyChanged(BR.title)
        }

}
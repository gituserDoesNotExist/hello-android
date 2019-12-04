package com.example.helloandroid.timerecording.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ConfigDTO : BaseObservable() {

    var selectedAppUser: String = ""
        @Bindable get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyPropertyChanged(BR.selectedAppUser)
            }
        }


    var savedAppUser: String = ""
        @Bindable get() = field
         set(value) {
             if (field != value) {
                 field = value
                 notifyPropertyChanged(BR.savedAppUser)
             }
         }


 }

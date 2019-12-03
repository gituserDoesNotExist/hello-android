package com.example.helloandroid.timerecording.view

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class ConfigDTO : BaseObservable() {

     var appUser: String = ""
        @Bindable get() = field
         set(value) {
             if (field != value) {
                 field = value
                 notifyPropertyChanged(BR.appUser)
             }
         }


 }

package com.example.helloandroid

import android.view.View
import androidx.databinding.ObservableField

class Person(var _firstName: String, var _lastName: String) {

    var firstName = ObservableField<String>(_firstName)
    var lastName = ObservableField<String>(_lastName)

    fun changeName(view: View) {
        println("i am called")
        firstName.set("new name")
        println("persons new first name is ${firstName.get()}")
    }
}
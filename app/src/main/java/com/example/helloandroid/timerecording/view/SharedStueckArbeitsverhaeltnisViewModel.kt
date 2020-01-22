package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnis

class SharedStueckArbeitsverhaeltnisViewModel : ViewModel() {

    lateinit var eventInfo: EventInfo
    lateinit var currentArbeitsverhaeltnis: StueckArbeitsverhaeltnis

    lateinit var fullName: String

}
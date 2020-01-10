package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis

class SharedZeitArbeitsverhaeltnisViewModel : ViewModel() {

    lateinit var eventInfo: EventInfo
    lateinit var currentArbeitsverhaeltnis: ZeitArbeitsverhaeltnis


}
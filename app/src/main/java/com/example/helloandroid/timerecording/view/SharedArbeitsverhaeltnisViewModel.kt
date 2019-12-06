package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.repository.ArbeitsverhaeltnisRepository

class SharedArbeitsverhaeltnisViewModel(private val arbeitsverhaeltnisRepository: ArbeitsverhaeltnisRepository) :
    ViewModel() {

    lateinit var currentArbeitsverhaeltnis: Arbeitsverhaeltnis

    fun deleteArbeitsverhaeltnis() {
        arbeitsverhaeltnisRepository.deleteArbeitsverhaeltnis(currentArbeitsverhaeltnis)
    }


}
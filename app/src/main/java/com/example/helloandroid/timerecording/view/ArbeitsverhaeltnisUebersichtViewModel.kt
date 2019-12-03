package com.example.helloandroid.timerecording.view

import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single

class ArbeitsverhaeltnisUebersichtViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) :
    ViewModel() {

    fun findAllArbeitsverhaeltnisse(): Single<List<Arbeitsverhaeltnis>> {
        return zeiterfassungRepository.fetchAllArbeitsverhaeltnisseFromRemoteCalender()
    }


    fun addEintrag(zeiterfassungDTO: ZeiterfassungDTO) {

    }


}
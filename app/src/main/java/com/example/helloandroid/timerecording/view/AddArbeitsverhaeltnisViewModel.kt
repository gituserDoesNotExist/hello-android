package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single

class AddArbeitsverhaeltnisViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    val config: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()
    private val arbeitsverhaeltnisMapper = ArbeitsverhaeltnisToDTOMapper()

    fun addArbeitsverhaeltnis(arbeitsverhaeltnisDTO: ArbeitsverhaeltnisErstellenDTO): Single<Long> {
        val arbeitsverhaeltnis = arbeitsverhaeltnisMapper.fromErstellenDtoToArbeitsverhaeltnis(arbeitsverhaeltnisDTO)
        return zeiterfassungRepository.addArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis)//
    }

}
package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.repository.AppConfigurationRepository
import com.example.helloandroid.timerecording.repository.ArbeitsverhaeltnisRepository
import io.reactivex.Single

class AddArbeitsverhaeltnisViewModel(private val arbeitsverhaeltnisRepository: ArbeitsverhaeltnisRepository,
                                     appConfigurationRepository: AppConfigurationRepository) : ViewModel() {

    val config: LiveData<CalendarConfiguration> = appConfigurationRepository.getConfiguration()
    private val arbeitsverhaeltnisMapper = ArbeitsverhaeltnisMapper()

    fun addArbeitsverhaeltnis(arbeitsverhaeltnisDTO: ArbeitsverhaeltnisErstellenDTO): Single<Long> {
        val arbeitsverhaeltnis = arbeitsverhaeltnisMapper.fromDto(arbeitsverhaeltnisDTO)
        return arbeitsverhaeltnisRepository.addArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis)//
    }

}
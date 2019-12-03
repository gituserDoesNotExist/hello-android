package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.repository.AppConfigurationRepository
import com.example.helloandroid.view.LiveDataNotInitializedException
import io.reactivex.Single

class AppConfigurationViewModel(private val appConfigurationRepository: AppConfigurationRepository) : ViewModel() {

    var calendarConfig: LiveData<CalendarConfiguration> = appConfigurationRepository.getConfiguration()

    fun downloadRemoteConfig(): Single<CalendarConfiguration> {
        return appConfigurationRepository.downloadRemoteConfiguration()
    }


    fun findParticipantByPosition(position: Int): String {
        return calendarConfig.value?.participants?.get(position) ?: throw aLiveDataNotInitializedException()
    }

    private fun aLiveDataNotInitializedException(): LiveDataNotInitializedException {
        return LiveDataNotInitializedException("Konfiguration wurde noch nicht initialisiert")
    }

    fun saveAppUser(appUser: String) {
        appConfigurationRepository.saveAppUser(appUser)
    }


}


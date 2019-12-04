package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.repository.AppConfigurationRepository
import io.reactivex.Single

class AppConfigurationViewModel(private val appConfigurationRepository: AppConfigurationRepository) : ViewModel() {

    var calendarConfig: LiveData<CalendarConfiguration> = appConfigurationRepository.getConfiguration()

    fun downloadRemoteConfig(): Single<CalendarConfiguration> {
        return appConfigurationRepository.downloadRemoteConfiguration()
    }


    fun saveAppUser(appUser: String) {
        appConfigurationRepository.saveAppUser(appUser)
    }


}


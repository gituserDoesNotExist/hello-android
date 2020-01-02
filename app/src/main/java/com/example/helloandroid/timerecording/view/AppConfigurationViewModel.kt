package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single
import ru.gildor.databinding.observables.ObservableString

class AppConfigurationViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    var calendarConfig: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()

    val savedAppUser = ObservableString()
    val selectedAppUser = ObservableString()

    fun downloadRemoteConfig(): Single<CalendarConfiguration> {
        return zeiterfassungRepository.downloadRemoteConfiguration()
    }


    fun saveAppUser(appUser: String) {
        zeiterfassungRepository.saveAppUser(appUser)
    }


}


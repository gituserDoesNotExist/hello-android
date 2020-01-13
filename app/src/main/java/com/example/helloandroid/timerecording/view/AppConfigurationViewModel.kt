package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single
import ru.gildor.databinding.observables.ObservableString

class AppConfigurationViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    var calendarConfig: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()

    var savedAppUser = ObservableString()
    var selectedAppUser = Person()

    fun downloadRemoteConfig(): Single<CalendarConfiguration> {
        return zeiterfassungRepository.downloadRemoteConfiguration()
    }


    fun saveAppUser(appUser: Person) {
        zeiterfassungRepository.saveAppUser(appUser)
    }


}


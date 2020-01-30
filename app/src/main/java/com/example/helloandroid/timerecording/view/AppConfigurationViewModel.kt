package com.example.helloandroid.timerecording.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.repository.ZeiterfassungRepository
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import ru.gildor.databinding.observables.ObservableString

class AppConfigurationViewModel(private val zeiterfassungRepository: ZeiterfassungRepository) : ViewModel() {

    var calendarConfig: LiveData<CalendarConfiguration> = zeiterfassungRepository.getConfiguration()
    lateinit var titles: List<String>

    var savedAppUser = ObservableString()
    var selectedAppUser = Person()

    private var fetchTitleDisposable: Disposable? = null

    fun loadTitles() {
        fetchTitleDisposable = zeiterfassungRepository.getTitles().subscribe(Consumer { titles = it })
    }

    fun downloadRemoteConfig(): Single<CalendarConfiguration> {
        return zeiterfassungRepository.downloadRemoteConfiguration()
    }


    fun saveAppUser(appUser: Person) {
        zeiterfassungRepository.saveAppUser(appUser)
    }

    override fun onCleared() {
        super.onCleared()
        fetchTitleDisposable?.dispose()
    }
}


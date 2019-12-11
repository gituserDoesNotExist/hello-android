package com.example.helloandroid.timerecording.repository

import androidx.lifecycle.LiveData
import com.example.helloandroid.timerecording.Arbeitsverhaeltnis
import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.LocalDate

class ZeiterfassungRepository(private val appConfigurationRepository: AppConfigurationRepository,
                              private val arbeitsverhaeltnisRepository: ArbeitsverhaeltnisRepository) {

    fun fetchArbeitsverhaeltnisseFromRemote(startDate: LocalDate, endDate: LocalDate): Single<TeamupEvents> {
        return arbeitsverhaeltnisRepository.fetchArbeitsverhaeltnisseFromRemote(startDate, endDate)
    }

    fun addArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis: Arbeitsverhaeltnis): Single<Long> {
        return appConfigurationRepository.getAppUser()//
            .subscribeOn(Schedulers.io())//
            .flatMap { arbeitsverhaeltnisRepository.addArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis, it) }
    }

    fun deleteArbeitsverhaeltnis(teamupEvent: TeamupEvent) {
        arbeitsverhaeltnisRepository.deleteTeamupEvent(teamupEvent)
    }

    fun getConfiguration(): LiveData<CalendarConfiguration> {
        return appConfigurationRepository.getConfiguration()
    }

    fun existsConfiguration(): Boolean {
        return appConfigurationRepository.existsConfiguration()
    }

    fun downloadRemoteConfiguration(): Single<CalendarConfiguration> {
        return appConfigurationRepository.downloadRemoteConfiguration()
    }

    fun saveAppUser(appUser: String) {
        appConfigurationRepository.saveAppUser(appUser)
    }


}

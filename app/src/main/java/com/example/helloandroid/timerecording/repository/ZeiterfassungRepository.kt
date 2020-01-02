package com.example.helloandroid.timerecording.repository

import androidx.lifecycle.LiveData
import com.example.helloandroid.timerecording.TeamupEvent
import com.example.helloandroid.timerecording.TeamupEvents
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.view.Suchkriterien
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ZeiterfassungRepository(private val appConfigurationRepository: AppConfigurationRepository,
                              private val arbeitsverhaeltnisRepository: ArbeitsverhaeltnisRepository) {

    fun fetchArbeitsverhaeltnisseFromRemote(suchkriterien: Suchkriterien): Single<TeamupEvents> {
        return arbeitsverhaeltnisRepository.fetchArbeitsverhaeltnisseFromRemote(suchkriterien)
    }

    fun addArbeitsverhaeltnisToRemoteCalendar(event: TeamupEvent): Single<Long> {
        return appConfigurationRepository.getAppUser()//
            .subscribeOn(Schedulers.io())//
            .map { event.apply { this.erstelltVon = it } }
            .flatMap { arbeitsverhaeltnisRepository.addTeamupEventToRemoteCalendar(it) }
    }

    fun updateArbeitsverhaeltnisInRemoteCalender(event: TeamupEvent): Single<TeamupEvent> {
        return appConfigurationRepository.getAppUser()//
            .subscribeOn(Schedulers.io())//
            .map { event.apply { this.erstelltVon = it } }
            .flatMap { arbeitsverhaeltnisRepository.updateArbeitsverhaeltnisInRemoteCalender(it) }
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

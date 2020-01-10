package com.example.helloandroid.timerecording.repository

import androidx.lifecycle.LiveData
import com.example.helloandroid.timerecording.Arbeitseinsaetze
import com.example.helloandroid.timerecording.EventInfo
import com.example.helloandroid.timerecording.StueckArbeitsverhaeltnis
import com.example.helloandroid.timerecording.ZeitArbeitsverhaeltnis
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.view.Suchkriterien
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class ZeiterfassungRepository(private val appConfigurationRepository: AppConfigurationRepository,
                              private val arbeitsverhaeltnisRepository: ArbeitsverhaeltnisRepository) {

    fun fetchArbeitseinsaetzeFromRemote(suchkriterien: Suchkriterien): Single<Arbeitseinsaetze> {
        return Single.fromCallable {
            appConfigurationRepository.getConfigurationSynchronous()
        }.subscribeOn(Schedulers.io())//
            .flatMap {
                arbeitsverhaeltnisRepository.fetchArbeitsverhaeltnisseFromRemote(suchkriterien, it)
            }.map {
                Arbeitseinsaetze(it)
            }
    }

    fun addZeitArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis: ZeitArbeitsverhaeltnis): Single<Long> {
        return appConfigurationRepository.getAppUser()//
            .subscribeOn(Schedulers.io())//
            .flatMap { arbeitsverhaeltnisRepository.addZeitArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis, it) }
    }

    fun addStueckArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis: StueckArbeitsverhaeltnis): Single<Long> {
        return appConfigurationRepository.getAppUser()//
            .subscribeOn(Schedulers.io())//
            .flatMap {
                arbeitsverhaeltnisRepository.addStueckArbeitsverhaeltnisToRemoteCalendar(arbeitsverhaeltnis, it)
            }
    }

    fun updateZeitArbeitsverhaeltnis(verhaeltnis: ZeitArbeitsverhaeltnis, info: EventInfo): Single<String> {
        return arbeitsverhaeltnisRepository.updateZeitArbeitsverhaeltnis(verhaeltnis,info)//
            .subscribeOn(Schedulers.io())//
    }

    fun updateStueckArbeitsverhaeltnis(arbeitsverhaeltnis: StueckArbeitsverhaeltnis, info: EventInfo): Single<String> {
        return arbeitsverhaeltnisRepository.updateStueckArbeitsverhaeltnis(arbeitsverhaeltnis,info)//
            .subscribeOn(Schedulers.io())//
    }


    fun deleteArbeitsverhaeltnis(eventInfo: EventInfo) {
        arbeitsverhaeltnisRepository.deleteArbeitseinsatz(eventInfo)
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

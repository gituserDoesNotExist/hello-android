package com.example.helloandroid.timerecording.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.helloandroid.DatabaseOperationException
import com.example.helloandroid.timerecording.config.Person
import com.example.helloandroid.timerecording.config.RemoteCalendarMetadata
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationDao
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationEntity
import com.example.helloandroid.timerecording.persistence.TitleDao
import com.example.helloandroid.timerecording.persistence.TitleEntity
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.web.TeamUpApi
import com.example.helloandroid.timerecording.web.remotemodel.ConfigurationWrapper
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class AppConfigurationRepository(private val calendarConfigurationDao: CalendarConfigurationDao,
                                 private val titleDao: TitleDao, private val teamUpApi: TeamUpApi) {

    private val calendarConfigMapper = CalendarConfigurationMapper()

    fun existsConfiguration(): Single<Boolean> {
        return Single.fromCallable<Boolean> { calendarConfigurationDao.existsConfiguration() }
            .subscribeOn(Schedulers.io())
    }

    fun getConfiguration(): LiveData<CalendarConfiguration> {
        return Transformations.map(calendarConfigurationDao.getConfiguration()) {
            calendarConfigMapper.fromConfigEntityToCalendarConfig(it)
        }
    }

    fun getConfigurationSynchronous(): CalendarConfiguration {
        val config = calendarConfigurationDao.getConfigurationSynchronous()
        return calendarConfigMapper.fromConfigEntityToCalendarConfig(config)
    }

    fun getAppUser(): Single<Person> {
        return Single.fromCallable { calendarConfigurationDao.getConfigurationSynchronous().appUser }
            .subscribeOn(Schedulers.io())
    }

    fun downloadRemoteConfiguration(): Single<CalendarConfiguration> {
        return teamUpApi.getConfiguration()//
            .subscribeOn(Schedulers.io())//
            .map {
                calendarConfigMapper.fromRemoteMetadataToCalendarConfigurationEntity(extractKalenderMetadata(it))
            }.map {
                upsertConfiguration(it)
            }
    }

    private fun upsertConfiguration(config: CalendarConfigurationEntity): CalendarConfiguration {
        if (calendarConfigurationDao.existsConfiguration()) {
            config.appUser = getConfigurationSynchronous().appUser
            calendarConfigurationDao.updateConfiguration(config)
        } else {
            calendarConfigurationDao.insertConfiguration(config)
        }
        return getConfigurationSynchronous()
    }

    private fun extractKalenderMetadata(it: ConfigurationWrapper): RemoteCalendarMetadata {
        val metadataString = it.configuration?.generalSettings?.about?.replace("<p>", "")?.replace("</p>", "")

        return Gson().fromJson(metadataString, RemoteCalendarMetadata::class.java)
    }

    fun saveAppUser(appUser: Person) {
        val config = calendarConfigurationDao.getConfigurationSynchronous()
        if (!config.teilnehmer.map { it.key }.contains(appUser.key)) {
            throw DatabaseOperationException("$appUser konnte in der bestehenden Konfiguration nicht gefunden werden")
        }
        calendarConfigurationDao.updateConfiguration(config.apply { this.appUser = appUser })
    }

    fun saveTitle(title: String): Long {
        return titleDao.insertTitle(TitleEntity(title))
    }


    fun getTitles(): Single<List<String>> {
        return titleDao.getTitles().subscribeOn(Schedulers.io())//
            .map { titles ->
                titles.map { it.title }
            }
    }


}
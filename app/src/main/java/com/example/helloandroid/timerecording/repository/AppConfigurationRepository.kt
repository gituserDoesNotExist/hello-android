package com.example.helloandroid.timerecording.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.helloandroid.DatabaseOperationException
import com.example.helloandroid.timerecording.RemoteCalendarMetadata
import com.example.helloandroid.timerecording.TeamupServiceGenerator
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationDao
import com.example.helloandroid.timerecording.persistence.CalendarConfigurationEntity
import com.example.helloandroid.timerecording.view.CalendarConfiguration
import com.example.helloandroid.timerecording.web.remotemodel.ConfigurationWrapper
import com.google.gson.Gson
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class AppConfigurationRepository(private val calendarConfigurationDao: CalendarConfigurationDao) {

    private val calendarConfigMapper = CalendarConfigurationMapper()
    private val teamUpApi = TeamupServiceGenerator.createService()

    fun existsConfiguration(): Boolean {
        return calendarConfigurationDao.existsConfiguration()
    }

    fun getConfiguration(): LiveData<CalendarConfiguration> {
        return Transformations.map(calendarConfigurationDao.getConfiguration()) {
            CalendarConfiguration(it.appUser, it.categories,
                it.participants)
        }
    }

    fun getAppUser(): Single<String> {
        return calendarConfigurationDao.getAppUser()
    }

    fun downloadRemoteConfiguration(): Single<CalendarConfiguration> {
        return teamUpApi.getConfiguration()//
            .subscribeOn(Schedulers.io()).map {
                calendarConfigMapper.fromRemoteMetadataToCalendarConfigurationEntity(extractKalenderMetadata(it))
            }.map {
                upsertConfiguration(it)
            }.map {
                CalendarConfiguration(it.appUser, it.categories,
                    it.participants)
            }
    }

    private fun upsertConfiguration(config: CalendarConfigurationEntity): CalendarConfigurationEntity {
        if (calendarConfigurationDao.existsConfiguration()) {
            calendarConfigurationDao.updateConfiguration(config)
        } else {
            calendarConfigurationDao.insertConfiguration(config)
        }
        return calendarConfigurationDao.getConfigurationSynchronous()
    }

    private fun extractKalenderMetadata(it: ConfigurationWrapper): RemoteCalendarMetadata {
        val metadataString = it.configuration?.generalSettings?.about?.replace("<p>", "")?.replace("</p>", "")

        return Gson().fromJson(metadataString, RemoteCalendarMetadata::class.java)
    }

    fun saveAppUser(appUser: String) {
        val config = calendarConfigurationDao.getConfigurationSynchronous()
        if (!config.participants.contains(appUser)) {
            throw DatabaseOperationException("$appUser konnte in der bestehenden Konfiguration nicht gefunden werden")
        }
        calendarConfigurationDao.updateConfiguration(config.apply { this.appUser = appUser })

    }


}
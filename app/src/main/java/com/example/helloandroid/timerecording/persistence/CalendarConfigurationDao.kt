package com.example.helloandroid.timerecording.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import io.reactivex.Single

@Dao
interface CalendarConfigurationDao {

    @Insert
    fun insertConfiguration(calendarConfigurationEntity: CalendarConfigurationEntity) : Long

    @Update
    fun updateConfiguration(calendarConfigurationEntity: CalendarConfigurationEntity)

    @Query("select * from CALENDER_CONFIGURATION_ENTITY c where c.ID = 1")
    fun getConfiguration() : LiveData<CalendarConfigurationEntity>

    @Query("select * from CALENDER_CONFIGURATION_ENTITY")
    fun getConfigurationTest() : List<CalendarConfigurationEntity>

    @Query("select c.appUser from CALENDER_CONFIGURATION_ENTITY c where c.ID = 1")
    fun getAppUser() : Single<String>

    @Query("select * from CALENDER_CONFIGURATION_ENTITY c where c.ID = 1")
    fun getConfigurationSynchronous() : CalendarConfigurationEntity

    @Query("select case when(exists(select * from CALENDER_CONFIGURATION_ENTITY where ID = 1)) then 1 else 0 end as it_exists from CALENDER_CONFIGURATION_ENTITY")
    fun existsConfiguration() : Boolean



}
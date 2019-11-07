package com.example.helloandroid.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface VerzichtDao {

    @Insert
    fun insertVerzicht(verzicht: Verzicht): Long

    @Update
    fun updateVerzicht(verzicht: Verzicht)

    @Query("SELECT * FROM VERZICHT verzicht where verzicht.id LIKE :id")
    fun findById(id: Long): Verzicht

    @Query("SELECT * FROM VERZICHT verzicht where verzicht.VERZICHT_NAME LIKE :type")
    fun findByName(type: String): Verzicht

    @Query("SELECT * FROM VERZICHT verzicht where verzicht.VERZICHT_NAME LIKE :type")
    fun findByNameLiveData(type: String): LiveData<Verzicht>

    @Query("SELECT * FROM VERZICHT")
    fun findAll(): List<Verzicht>

    @Query("SELECT * FROM VERZICHT")
    fun findAllVerzichte(): LiveData<List<Verzicht>>


}
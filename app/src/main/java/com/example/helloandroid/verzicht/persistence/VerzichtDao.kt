package com.example.helloandroid.verzicht.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VerzichtDao {

    @Insert
    fun insertVerzicht(verzicht: Verzicht): Long

    @Update
    fun updateVerzicht(verzicht: Verzicht)

    @Delete
    fun deleteVerzicht(verzicht: Verzicht)

    @Query("SELECT * FROM VERZICHT verzicht where verzicht.id LIKE :id")
    fun getById(id: Long): LiveData<Verzicht>

    @Query("SELECT * FROM VERZICHT verzicht where verzicht.VERZICHT_NAME LIKE :type")
    fun getByName(type: String): LiveData<Verzicht>

    @Query("SELECT * FROM VERZICHT")
    fun getAll(): LiveData<List<Verzicht>>

    @Query("SELECT * FROM VERZICHT")
    fun getAllTest(): List<Verzicht>

}
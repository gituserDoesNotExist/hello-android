package com.example.helloandroid.finances.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AusgabeDao {

    @Insert
    fun insertAusgabe(ausgaben: Ausgabe)

    @Insert
    fun insertAusgaben(ausgaben: List<Ausgabe>)

    @Query("select * from ausgabe a where a.POSTEN_ID = :postenId")
    fun getAusgabenByPostenId(postenId: Long) : List<Ausgabe>

    @Query("select * from AUSGABE")
    fun getAll() : List<Ausgabe>

}
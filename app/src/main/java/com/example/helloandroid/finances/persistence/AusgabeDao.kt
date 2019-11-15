package com.example.helloandroid.finances.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AusgabeDao {

    @Insert
    fun insertAusgabe(ausgaben: AusgabeEntity)

    @Insert
    fun insertAusgaben(ausgaben: List<AusgabeEntity>)

    @Query("select * from AUSGABE_ENTITY a where a.POSTEN_ID = :postenId")
    fun getAusgabenByPostenId(postenId: Long) : List<AusgabeEntity>

    @Query("select * from AUSGABE_ENTITY")
    fun getAll() : List<AusgabeEntity>

}
package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AusgabeDao {

    @Insert
    fun insertAusgabe(ausgaben: AusgabeEntity)

    @Query("select * from AUSGABE_ENTITY a where a.POSTEN_ID = :postenId")
    fun getAusgabenByPostenId(postenId: Long) : LiveData<List<AusgabeEntity>>

    @Query("delete from AUSGABE_ENTITY where POSTEN_ID = :postenId")
    fun deleteAusgabenWithPostenId(postenId: Long)

}
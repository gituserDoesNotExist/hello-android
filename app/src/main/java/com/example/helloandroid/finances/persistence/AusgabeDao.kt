package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AusgabeDao {

    @Insert
    fun insertAusgabe(ausgabe: AusgabeEntity)

    @Update
    fun updateAusgabe(ausgabe: AusgabeEntity)

    @Query("select case when(exists(select * from AUSGABE_ENTITY a where a.id = :ausgabeId)) then 1 else 0 end as it_exists from AUSGABE_ENTITY")
    fun doesAusgabeExist(ausgabeId: Long) : Boolean

    @Query("delete from AUSGABE_ENTITY where ID = :ausgabeId")
    fun deleteAusgabe(ausgabeId: Long)

    @Query("select * from AUSGABE_ENTITY a where a.POSTEN_ID = :postenId")
    fun getAusgabenByPostenId(postenId: Long) : LiveData<List<AusgabeEntity>>

    @Query("delete from AUSGABE_ENTITY where POSTEN_ID = :postenId")
    fun deleteAusgabenWithPostenId(postenId: Long)

}
package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface PostenWithAusgabenDao {

    @Query("SELECT * FROM POSTEN_ENTITY")
    fun getAllPostenWithAusgaben(): LiveData<List<PostenWithAusgabenEntity>>

    @Query("select * from POSTEN_ENTITY p where p.ID = :id")
    fun getById(id: Long) : PostenWithAusgabenEntity

    @Query("select p.ID as id,p.NAME as name, sum(a.WERT) as gesamtausgaben from POSTEN_ENTITY p join AUSGABE_ENTITY a on p.ID = a.POSTEN_ID group by p.ID,p.NAME")
    fun getPostenStubs() : LiveData<List<PostenStubEntity>>

    @Query("SELECT * FROM POSTEN_ENTITY")
    fun getAllPostenWithAusgabenTest(): List<PostenWithAusgabenEntity>

}
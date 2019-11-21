package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query

@Dao
interface PostenWithAusgabenDao {

    @Query("select * from POSTEN_ENTITY p where p.ID = :id")
    fun getPostenById(id: Long) : LiveData<PostenWithAusgabenEntity>

    @Query("select p.ID as id,p.NAME as name, sum(a.WERT) as gesamtausgaben from POSTEN_ENTITY p left outer join AUSGABE_ENTITY a on p.ID = a.POSTEN_ID group by p.ID,p.NAME")
    fun getPostenStubs() : LiveData<List<PostenStubEntity>>



}
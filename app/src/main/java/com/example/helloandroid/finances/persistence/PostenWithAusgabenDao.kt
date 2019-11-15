package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostenWithAusgabenDao {

    @Query("SELECT * FROM POSTEN_ENTITY")
    fun getAllPostenWithAusgaben(): LiveData<List<PostenWithAusgabenEntity>>

    @Query("select * from POSTEN_ENTITY p where p.ID = :id")
    fun getById(id: Long) : PostenWithAusgabenEntity

}
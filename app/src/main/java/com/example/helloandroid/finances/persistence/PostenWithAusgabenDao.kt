package com.example.helloandroid.finances.persistence

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PostenWithAusgabenDao {

    @Query("SELECT * FROM POSTEN_ENTITY")
    fun getAllPostenWithAusgaben(): List<PostenWithAusgabenEntity>

    @Query("select * from POSTEN_ENTITY p where p.ID = :id")
    fun getById(id: Long) : PostenWithAusgabenEntity

}
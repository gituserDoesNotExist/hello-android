package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostenDao {

    @Insert
    fun insertPosten(posten: PostenEntity) : Long

    @Query("select * from POSTEN_ENTITY p where p.ID = :id")
    fun getById(id: Long) : PostenEntity

}
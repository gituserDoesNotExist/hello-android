package com.example.helloandroid.finances.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostenDao {

    @Insert
    fun insertPosten(posten: Posten) : Long

    @Query("select * from posten p where p.ID = :id")
    fun getById(id: Long) : Posten

    @Query("select * from posten")
    fun getAll() : List<Posten>

    @Query("select * from posten")
    fun getAllLiveData() : LiveData<List<Posten>>
}
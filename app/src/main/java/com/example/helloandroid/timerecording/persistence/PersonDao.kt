package com.example.helloandroid.timerecording.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {

    @Insert
    fun insertPerson(person: PersonEntity) : Long

    @Query("select * from PERSON_ENTITY p where p.ID = :id")
    fun getById(id: Long) : PersonEntity


}
package com.example.helloandroid.timerecording.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Single

@Dao
interface TitleDao {

    @Insert
    fun insertTitle(titleEntity: TitleEntity) : Long

    @Query("DELETE FROM TITLE_ENTITY")
    fun deleteAll()

    @Query("select * from TITLE_ENTITY")
    fun getTitles() : Single<List<TitleEntity>>



}
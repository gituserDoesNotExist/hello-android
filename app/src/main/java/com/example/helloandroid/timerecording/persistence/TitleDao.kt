package com.example.helloandroid.timerecording.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface TitleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTitle(titleEntity: TitleEntity): Long

    @Query("select * from TITLE_ENTITY")
    fun getTitles(): Single<List<TitleEntity>>


}
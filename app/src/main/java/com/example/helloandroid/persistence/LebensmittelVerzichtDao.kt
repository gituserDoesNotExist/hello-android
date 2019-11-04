package com.example.helloandroid.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LebensmittelVerzichtDao {
    @Insert
    fun insertLebensmittelVerzicht(lebensmittelVerzicht: LebensmittelVerzicht)


    @Query("SELECT * FROM LEBENSMITTEL_VERZICHT verzicht where verzicht.name LIKE :type")
    fun findByLebensmittelType(type: LebensmittelType): LebensmittelVerzicht


    @Query("SELECT * FROM LEBENSMITTEL_VERZICHT")
    fun findAll(): List<LebensmittelVerzicht>


}
package com.example.helloandroid.timerecording.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.TypeConverters
import com.example.helloandroid.persistence.BaseEntity
import com.example.helloandroid.timerecording.Rolle

@Entity(tableName = "PERSON_ENTITY",indices = [Index(value = ["ID"])])
class PersonEntity : BaseEntity() {

    @ColumnInfo(name = "NAME")
    lateinit var name: String
    @ColumnInfo(name = "ROLLE")
    @TypeConverters(RolleConverter::class)
    lateinit var rolle: Rolle

}
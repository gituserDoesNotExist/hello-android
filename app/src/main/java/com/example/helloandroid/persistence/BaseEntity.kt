package com.example.helloandroid.persistence

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

open class BaseEntity {

    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
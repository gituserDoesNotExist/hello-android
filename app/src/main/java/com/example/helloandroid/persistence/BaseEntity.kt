package com.example.helloandroid.persistence

import androidx.room.PrimaryKey

open class BaseEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

}
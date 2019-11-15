package com.example.helloandroid.finances.persistence

import androidx.room.Embedded
import androidx.room.Relation
import com.example.helloandroid.finances.Ausgabe
import com.example.helloandroid.finances.Posten

class PostenWithAusgabenEntity {

    @Embedded
    lateinit var posten: Posten

    @Relation(parentColumn = "ID", entityColumn = "POSTEN_ID")
     lateinit var ausgaben: List<Ausgabe>

}
package com.example.helloandroid.finances.persistence

import com.example.helloandroid.finances.Posten

class PostenEntityToPostenMapper  {

    fun asPosten(postenEntity: PostenEntity): Posten {
        val posten = Posten(postenEntity.name)
        posten.id = postenEntity.id
        return posten
    }

}
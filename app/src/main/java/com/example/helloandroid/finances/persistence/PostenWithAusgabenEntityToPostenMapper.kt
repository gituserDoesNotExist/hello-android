package com.example.helloandroid.finances.persistence

import com.example.helloandroid.finances.Posten

class PostenWithAusgabenEntityToPostenMapper {

    private val ausgabeEntityToAusgabMapper = AusgabeEntityToAusgabeMapper()
    private val postenEntityToPostenMapper = PostenEntityToPostenMapper()

    fun fromPostenWithAusgabenToPosten(postenWithAusgaben: PostenWithAusgabenEntity): Posten {
        val posten = postenEntityToPostenMapper.asPosten(postenWithAusgaben.posten)
        posten.ausgaben.addAll(ausgabeEntityToAusgabMapper.asAusgaben(postenWithAusgaben.ausgaben))
        return posten
    }

}
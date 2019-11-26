package com.example.helloandroid.finances.persistence

import com.example.helloandroid.finances.Ausgabe
import java.util.stream.Collectors

class AusgabeEntityToAusgabeMapper {


    fun asAusgaben(ausgabenEntities: List<AusgabeEntity>): List<Ausgabe> {
        return ausgabenEntities.stream().map(this::asAusgabe).collect(Collectors.toList())
    }

    private fun asAusgabe(value: AusgabeEntity): Ausgabe {
        return Ausgabe(value.id, value.wert, value.datum, value.beschreibung, value.postenId)
    }

    fun asAusgabeEntity(ausgabe: Ausgabe): AusgabeEntity {
        return AusgabeEntity(ausgabe.wert, ausgabe.beschreibung, ausgabe.datum).apply { postenId = ausgabe.postenId }
    }

}
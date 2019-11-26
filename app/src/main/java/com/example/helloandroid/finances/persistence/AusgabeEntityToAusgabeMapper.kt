package com.example.helloandroid.finances.persistence

import com.example.helloandroid.finances.Ausgabe
import java.util.stream.Collectors

class AusgabeEntityToAusgabeMapper {

    fun asAusgabe(ausgabeEntity: AusgabeEntity): Ausgabe {
        return Ausgabe(ausgabeEntity.wert, ausgabeEntity.datum, ausgabeEntity.beschreibung, ausgabeEntity.postenId)
    }

    fun asAusgaben(ausgabenEntities: List<AusgabeEntity>): List<Ausgabe> {
        return ausgabenEntities.stream().map(this::asAusgabe).collect(Collectors.toList())
    }

    fun asAusgabeEntity(ausgabe: Ausgabe): AusgabeEntity {
        return AusgabeEntity(ausgabe.wert, ausgabe.beschreibung, ausgabe.datum).apply { postenId = ausgabe.postenId }
    }

}
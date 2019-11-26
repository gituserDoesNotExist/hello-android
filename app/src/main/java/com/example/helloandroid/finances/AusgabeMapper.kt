package com.example.helloandroid.finances

import com.example.helloandroid.finances.view.AusgabeDTO
import org.threeten.bp.LocalDateTime

class AusgabeMapper {

    fun fromDTO(ausgabeDto: AusgabeDTO): Ausgabe {
        val date = ausgabeDto.datum
        val time = ausgabeDto.uhrzeit

        return Ausgabe(ausgabeDto.id, ausgabeDto.wert, LocalDateTime.of(date, time), ausgabeDto.beschreibung)
    }

}
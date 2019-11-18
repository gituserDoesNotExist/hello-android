package com.example.helloandroid.finances

import com.example.helloandroid.finances.persistence.AusgabeEntity
import com.example.helloandroid.finances.view.AusgabeDTO
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AusgabeMapper {

    fun fromDTO(ausgabeDto: AusgabeDTO): AusgabeEntity {
        var currentDateTime = LocalDateTime.of(ausgabeDto.datum,ausgabeDto.uhrzeit)

        return AusgabeEntity(ausgabeDto.wert, ausgabeDto.beschreibung, currentDateTime)
    }

}
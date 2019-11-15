package com.example.helloandroid.finances

import com.example.helloandroid.finances.persistence.AusgabeEntity
import com.example.helloandroid.finances.view.AusgabeDTO
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AusgabeMapper {

    fun fromDTO(ausgabeDto: AusgabeDTO): AusgabeEntity {
        val dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        var currentDateTime = LocalDateTime.parse("${ausgabeDto.datum} ${ausgabeDto.uhrzeit}", dtf)

        return AusgabeEntity(BigDecimal(ausgabeDto.wert), ausgabeDto.beschreibung, currentDateTime)
    }

}
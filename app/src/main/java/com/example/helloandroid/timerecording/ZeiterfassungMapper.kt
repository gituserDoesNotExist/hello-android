package com.example.helloandroid.timerecording

import com.example.helloandroid.timerecording.view.ZeiterfassungDTO

class ZeiterfassungMapper {

    fun asZeiterfassung(dto: ZeiterfassungDTO): Zeiterfassung {
        return Zeiterfassung(dto.datumZeiterfassung,dto.stunden,dto.kommentar)
    }

}
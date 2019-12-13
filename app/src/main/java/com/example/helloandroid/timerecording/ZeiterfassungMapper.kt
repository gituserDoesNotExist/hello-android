package com.example.helloandroid.timerecording

import com.example.helloandroid.timerecording.view.ArbeitsverhaeltnisDTO

class ZeiterfassungMapper {

    fun asZeiterfassung(dto: ArbeitsverhaeltnisDTO): Zeiterfassung {
        return Zeiterfassung(dto.datumZeiterfassung,dto.dauer,dto.kommentar)
    }

}
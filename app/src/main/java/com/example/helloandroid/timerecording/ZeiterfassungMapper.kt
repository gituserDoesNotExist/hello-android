package com.example.helloandroid.timerecording

import com.example.helloandroid.timerecording.view.ArbeitsverhaeltnisErstellenDTO

class ZeiterfassungMapper {

    fun asZeiterfassung(erstellenDto: ArbeitsverhaeltnisErstellenDTO): Zeiterfassung {
        return Zeiterfassung(erstellenDto.datumZeiterfassung,erstellenDto.dauer,erstellenDto.kommentar)
    }

}
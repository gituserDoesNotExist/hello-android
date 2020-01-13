package com.example.helloandroid.timerecording.config

import java.math.BigDecimal

class Fahrzeug : Maschine {

    constructor() : super()

    constructor(key: String, bezeichnung: String, stundensatz: BigDecimal) : super(key,bezeichnung,stundensatz)


}
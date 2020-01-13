package com.example.helloandroid.timerecording.config

import java.math.BigDecimal

open class Maschine(override val key: String = "", val bezeichnung: String = "",
                    override val stundensatz: BigDecimal = BigDecimal.ZERO) : Abrechenbar
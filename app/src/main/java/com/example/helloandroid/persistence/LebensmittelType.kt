package com.example.helloandroid.persistence

import java.lang.RuntimeException

enum class LebensmittelType {

    CHOCOLATE("Chocolate");

    var lebensmittelName: String;

    private constructor(name: String) {
        this.lebensmittelName = name
    }

    companion object {

        fun fromLebensmittelName(name: String): LebensmittelType {
            return values().toList().stream()//
                .filter{ lebensmittelType -> lebensmittelType.lebensmittelName.equals(name)}//
                .findFirst().orElseThrow { TypeNotFoundException("Nothing found for $name") }
        }

    }
}
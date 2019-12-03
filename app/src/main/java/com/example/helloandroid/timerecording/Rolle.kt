package com.example.helloandroid.timerecording

enum class Rolle(val id: Long) {

    NICHT_ZUGEWIESEN(1),//
    AKTUELLER_APP_USER(2);


    companion object {
        fun fromId(id: Long): Rolle {
            return values().toList().stream().filter { it.id == id }.findFirst().orElseThrow { aException(id) }
        }

        private fun aException(id: Long) =
            IllegalArgumentException("Für $id konnte keine passende Rolle gefunden werden!")
    }

}
package com.example.helloandroid.timerecording

import android.content.Context
import com.example.helloandroid.R

object KeineAuswahl {

    lateinit var value : String

    fun init(context: Context) {
        value = context.resources.getString(R.string.keine_auswahl)
    }

}
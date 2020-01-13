package com.example.helloandroid

import android.content.Context

class ConfirmDeleteDialog(context: Context?, onConfirmListener: () -> Unit) :
    HelloAlertDialog(context, "", onConfirmListener) {

    init {
        setTitle(context?.resources?.getString(R.string.loeschen))
    }


}
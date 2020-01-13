package com.example.helloandroid

import android.app.AlertDialog
import android.content.Context

open class HelloAlertDialog(context: Context?, title: String, onConfirmListener: () -> Unit) :
    AlertDialog.Builder(context) {

    init {
        this.setTitle(title).setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                onConfirmListener()
            }.setNegativeButton(android.R.string.no, null)

    }

}
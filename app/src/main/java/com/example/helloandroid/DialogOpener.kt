package com.example.helloandroid

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity

object DialogOpener {


    fun openDialog(activity: FragmentActivity, dialog: DialogFragment, tagName: String) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        val prev = activity.supportFragmentManager.findFragmentByTag(tagName)
        if (prev != null) {
            transaction.remove(prev)
        }
        transaction.addToBackStack(null)
        dialog.show(transaction, tagName)
    }


}
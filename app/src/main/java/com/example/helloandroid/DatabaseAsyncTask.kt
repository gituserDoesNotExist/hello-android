package com.example.helloandroid

import android.os.AsyncTask
import java.lang.ref.WeakReference
import java.util.*

class DatabaseAsyncTask<INPUT, RESULT>(private val backgroundOperationFunc: (INPUT) -> RESULT,
                                       private val dbOperationSuccessfulListener: WeakReference<(RESULT) -> Unit> = WeakReference { _ -> },
                                       private val dbOperationFailedListener: WeakReference<(String) -> Unit> = WeakReference { _ ->}) :
    AsyncTask<INPUT, String, Optional<RESULT>>() {

    private lateinit var databaseOperationException: DatabaseOperationException

    override fun doInBackground(vararg params: INPUT): Optional<RESULT> {
        if (params.isEmpty()) throw IllegalArgumentException("No arguments provided!")
        try {
            return Optional.ofNullable(backgroundOperationFunc(params[0]))
        } catch (e: DatabaseOperationException) {
            this.databaseOperationException = e
            cancel(true)
        }
        return Optional.empty()
    }

    override fun onPostExecute(result: Optional<RESULT>) {
        dbOperationSuccessfulListener.get()?.let { result.ifPresent { res -> it(res) } }
    }

    override fun onCancelled() {
        dbOperationFailedListener.get()?.let { it(databaseOperationException.message?: "Unknown error") }
    }

}
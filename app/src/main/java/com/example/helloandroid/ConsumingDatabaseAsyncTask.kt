package com.example.helloandroid

import android.os.AsyncTask
import java.lang.ref.WeakReference
import java.util.*

class ConsumingDatabaseAsyncTask<RESULT>(private val backgroundOperationConsumer: () -> RESULT,
                                         private val dbOperationSuccessfulListener: WeakReference<(RESULT) -> Unit> = WeakReference { _ -> },
                                         private val dbOperationFailedListener: WeakReference<(String) -> Unit> = WeakReference { _ -> }) :
    AsyncTask<Unit, String, Optional<RESULT>>() {

    private lateinit var databaseOperationException: DatabaseOperationException

    override fun doInBackground(vararg params: Unit): Optional<RESULT> {
        try {
            return Optional.ofNullable(backgroundOperationConsumer())
        } catch (e: DatabaseOperationException) {
            this.databaseOperationException = e
            cancel(true)
        } catch (e: Exception) {
            this.databaseOperationException = DatabaseOperationException("This should not happen!")
            cancel(true)
        }
        return Optional.empty()
    }

    override fun onPostExecute(result: Optional<RESULT>) {
        dbOperationSuccessfulListener.get()?.let { result.ifPresent { res -> it(res) } }
    }

    override fun onCancelled() {
        dbOperationFailedListener.get()?.let { it(databaseOperationException.message ?: "Unknown error") }
    }

}
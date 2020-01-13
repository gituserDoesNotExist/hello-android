package com.example.helloandroid

import com.google.android.material.snackbar.Snackbar


class HelloExceptionHandler(private val baseActivity: BaseActivity) : Thread.UncaughtExceptionHandler {

    private val rootHandler: Thread.UncaughtExceptionHandler =
        Thread.getDefaultUncaughtExceptionHandler() ?: throw RuntimeException("Something went wrong...")

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        when (val cause = ex.cause) {
            is NoNetworkException -> {
                createSnackbar(cause.message ?: "").setAction("Try again") {
                    baseActivity.reloadCallback()
                }.show()
                baseActivity.hideProgressbarCallback()
            }
            is HelloException -> {
                createSnackbar("Unknown Error. Please Restart App.").show()
                baseActivity.hideProgressbarCallback()
            }
            else -> {
                rootHandler.uncaughtException(thread, ex)

            }
        }
    }

    private fun createSnackbar(charSequence: CharSequence) =
        Snackbar.make(baseActivity.getParentViewForSnackbar(), charSequence, Snackbar.LENGTH_INDEFINITE)


}
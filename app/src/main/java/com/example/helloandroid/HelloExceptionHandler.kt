package com.example.helloandroid

import com.google.android.material.snackbar.Snackbar


class HelloExceptionHandler(private val baseActivity: BaseActivity) : Thread.UncaughtExceptionHandler {

    private val rootHandler: Thread.UncaughtExceptionHandler =
        Thread.getDefaultUncaughtExceptionHandler() ?: throw RuntimeException("Something went wrong...")

    init {
        Thread.setDefaultUncaughtExceptionHandler(this)
    }

    override fun uncaughtException(thread: Thread, ex: Throwable) {
        when (ex) {
            is HelloException -> createSnackbar(ex.message ?: "Unknown error")
            is UninitializedPropertyAccessException -> baseActivity.goToHome()
        }
        handleWrappedException(ex)
    }

    private fun handleWrappedException(ex: Throwable) {
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
                createSnackbar("This should not happen...")
            }
        }
    }

    private fun createSnackbar(charSequence: CharSequence) =
        Snackbar.make(baseActivity.getParentViewForSnackbar(), charSequence, Snackbar.LENGTH_INDEFINITE)


}
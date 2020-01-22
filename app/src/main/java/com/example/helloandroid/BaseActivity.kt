package com.example.helloandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    var hideProgressbarCallback: () -> Unit = {}
    var reloadCallback: () -> Unit = {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HelloExceptionHandler(this)
    }

    abstract fun getParentViewForSnackbar() : View

    fun goToHome() {
        startActivity(Intent(this, MainActivity::class.java))
    }

}
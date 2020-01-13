package com.example.helloandroid

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


}
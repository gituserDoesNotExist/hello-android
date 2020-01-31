package com.example.helloandroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.helloandroid.timerecording.view.ZeiterfassungViewModelFactory

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

    fun <T : ViewModel> provideViewModel(clazz: Class<T>): T {
        return ViewModelProviders.of(this,ZeiterfassungViewModelFactory(this.applicationContext)).get(clazz)
    }

}
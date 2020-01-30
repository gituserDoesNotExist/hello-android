package com.example.helloandroid.timerecording.view

import android.text.InputFilter
import android.view.View
import androidx.databinding.BindingAdapter
import com.example.helloandroid.HelloInputType
import com.example.helloandroid.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@BindingAdapter("errorneous")
fun TextInputLayout.seterrorneous(errorneous: Boolean) {
    this.isErrorEnabled = errorneous
    if (errorneous) {
        this.error = this.resources.getString(R.string.wert_eingeben)
    } else {
        this.error = ""
    }
}

@BindingAdapter("inputType")
fun TextInputEditText.setInputType(helloInputType: HelloInputType?) {
    helloInputType?.let {
        this.inputType = it.inputType
        if (it == HelloInputType.NUMBER || it == HelloInputType.DECIMAL) {
            this.filters = arrayOf(InputFilter.LengthFilter(5))
        }
    }
}



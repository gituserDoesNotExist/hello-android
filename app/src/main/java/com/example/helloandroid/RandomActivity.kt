package com.example.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.helloandroid.databinding.ActivityRandomBinding

class RandomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        val binding : ActivityRandomBinding = DataBindingUtil.setContentView<ActivityRandomBinding>(this, R.layout.activity_random)
        val person: Person = Person("first name", "last name")
        binding.person = person
    }
}

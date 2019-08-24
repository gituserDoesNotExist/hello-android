package com.example.helloandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.helloandroid.databinding.ActivityChocolateBinding

class ChocolateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityChocolateBinding>(this, R.layout.activity_chocolate)
        val chocolateViewModel = ChocolateViewModel()
        binding.chocolateViewModel = chocolateViewModel
    }
}

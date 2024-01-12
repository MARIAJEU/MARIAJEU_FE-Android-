package com.example.mariajeu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mariajeu.databinding.ActivityLogin2Binding

class Login2Activity : AppCompatActivity() {

    lateinit var binding: ActivityLogin2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
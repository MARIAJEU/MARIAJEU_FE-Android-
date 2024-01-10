package com.example.mariajeu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mariajeu.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(){

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}
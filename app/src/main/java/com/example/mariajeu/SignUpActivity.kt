package com.example.mariajeu

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mariajeu.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intentSignUp = Intent(this, Login2Activity::class.java)

        binding.btnNext.setOnClickListener {
            startActivity(intentSignUp)
        }
        binding.btnCertification.setOnClickListener {
            val helper = AppSignatureHelper(this)
            val hash = helper.getAppSignatures()?.get(0)
        }
    }
}
package com.example.mariajeu

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
        binding.etInputPwdAgain.addTextChangedListener(object : TextWatcher {
            // 입력이 끝났을 때
            // 비밀번호 일치하는지 확인
            override fun afterTextChanged(p0: Editable?) {
                // 비밀번호 일치 시
                if (binding.etInputPwd.text.toString().equals(binding.etInputPwdAgain.text.toString())) {
                    binding.tvIncorrectPw.visibility = View.GONE
                }

                // 비밀번호 불일치시
                else {
                    binding.tvIncorrectPw.visibility = View.VISIBLE
                }
            }

            //  입력하기 전
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 비밀번호 일치 시
                if (binding.etInputPwd.text.toString().equals(binding.etInputPwdAgain.text.toString())) {
                    binding.tvIncorrectPw.visibility = View.GONE
                }

                // 비밀번호 불일치시
                else {
                    binding.tvIncorrectPw.visibility = View.VISIBLE
                }
            }
        })
    }
}
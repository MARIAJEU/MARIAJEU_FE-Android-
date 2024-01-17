package com.example.mariajeu

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.mariajeu.databinding.ActivitySignupBinding
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, Login2Activity::class.java)

        // 다음 버튼 눌렀을 때 프로필 사진 화면으로 전환
        binding.btnNext.setOnClickListener {
            Log.d("TEST이름TEST이름", binding.etName.text.toString())
            intent.putExtra("이름", binding.etName.text.toString())
            startActivity(intent)
        }

        // TODO 본인인증 -> 안 됨...
        binding.btnCertification.setOnClickListener {
            val helper = AppSignatureHelper(this)
            val hash = helper.getAppSignatures()?.get(0)
        }

        // 비밀번호 일치 여부 확인
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

        // 버튼 눌렀을 때 동의 -----------------------------------------------------------------------

        binding.btnAllOff.setOnClickListener {
            binding.btnAllOff.visibility = View.GONE
            binding.btnAllOn.visibility = View.VISIBLE

            binding.btnYakgwanOff.visibility = View.GONE
            binding.btnYakgwanOn.visibility = View.VISIBLE
        }

        binding.btnAllOn.setOnClickListener {
            binding.btnAllOff.visibility = View.VISIBLE
            binding.btnAllOn.visibility = View.GONE

            binding.btnYakgwanOff.visibility = View.VISIBLE
            binding.btnYakgwanOn.visibility = View.GONE
        }

        binding.btnYakgwanOff.setOnClickListener {
            binding.btnYakgwanOff.visibility = View.GONE
            binding.btnYakgwanOn.visibility = View.VISIBLE
        }

        binding.btnYakgwanOn.setOnClickListener {
            binding.btnYakgwanOff.visibility = View.VISIBLE
            binding.btnYakgwanOn.visibility = View.GONE
        }

        //-----------------------------------------------------------------------

    }
}
package com.example.mariajeu

import android.app.PendingIntent
import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mariajeu.databinding.ActivitySignupBinding
import com.google.android.gms.common.GoogleApiAvailability
import java.lang.Exception
import kotlin.random.Random


class SignUpActivity : AppCompatActivity() {



    lateinit var binding: ActivitySignupBinding

    companion object {
        lateinit var userId: String
        lateinit var password: String
        lateinit var username: String
        lateinit var emailAddr: String

        var agreeCheck1 = false
        var agreeCheck2 = false
        var optionCheck = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, Login2Activity::class.java)
        // 랜덤으로 네 자리 수 만들기 (인증번호로 사용)
        val random = Random(System.currentTimeMillis())
        val randomNum = random.nextInt(1000, 10000)

        // 다음 버튼 눌렀을 때 프로필 사진 화면으로 전환
        binding.btnNext.setOnClickListener {

            userId = binding.etInputId.text.toString()
            password = binding.etInputPwd.text.toString()
            username = binding.etName.text.toString()
            emailAddr = binding.etEmail.text.toString()

            val gpsVersion = packageManager.getPackageInfo(GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE, 0).versionCode
            Log.d("TEST버젼체크TEST버젼체크", gpsVersion.toString())

            // 필수 정보 기입 안 했을 시 다음 화면으로 안 넘어가도록 함
            if (binding.etInputId.text.toString().isEmpty()) {
                binding.tvEssentialInfo.visibility = View.VISIBLE
            }
            else if (binding.etInputPhone.text.toString().isEmpty()) {
                binding.tvEssentialInfo2.visibility = View.VISIBLE
            }
            else {
                Log.d("TEST이름TEST이름", binding.etName.text.toString())
                intent.putExtra("이름", binding.etName.text.toString())
                startActivity(intent)
            }
        }

        // TODO 본인인증 -> 안 됨...
        binding.btnCertification.setOnClickListener {
            val phoneNumber = binding.etInputPhone.text.toString()
            Log.d("TEST전번TEST전번", phoneNumber)

            sendSmsMessage(this,  phoneNumber, "[마리아주 본인 확인] 인증번호: $randomNum")
        }

        binding.btnConfirm.setOnClickListener {
            if (randomNum.toString().equals(binding.etCertificationNum.text.toString())) {
                binding.tvCheckCertification.visibility = View.VISIBLE
            }
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

            binding.btnPersoninfoOff.visibility = View.GONE
            binding.btnPersoninfoOn.visibility = View.VISIBLE

            agreeCheck1 = true
            agreeCheck2 = true
        }

        binding.btnAllOn.setOnClickListener {
            binding.btnAllOff.visibility = View.VISIBLE
            binding.btnAllOn.visibility = View.GONE

            binding.btnYakgwanOff.visibility = View.VISIBLE
            binding.btnYakgwanOn.visibility = View.GONE

            binding.btnPersoninfoOff.visibility = View.VISIBLE
            binding.btnPersoninfoOn.visibility = View.GONE

            agreeCheck1 = false
            agreeCheck2 = false
        }

        binding.btnYakgwanOff.setOnClickListener {
            binding.btnYakgwanOff.visibility = View.GONE
            binding.btnYakgwanOn.visibility = View.VISIBLE

            agreeCheck1 = true
        }

        binding.btnYakgwanOn.setOnClickListener {
            binding.btnYakgwanOff.visibility = View.VISIBLE
            binding.btnYakgwanOn.visibility = View.GONE

            agreeCheck1 = false
        }

        binding.btnPersoninfoOff.setOnClickListener {
            binding.btnPersoninfoOff.visibility = View.GONE
            binding.btnPersoninfoOn.visibility = View.VISIBLE

            agreeCheck2 = true
        }

        binding.btnPersoninfoOn.setOnClickListener {
            binding.btnPersoninfoOff.visibility = View.VISIBLE
            binding.btnPersoninfoOn.visibility = View.GONE

            agreeCheck2 = false
        }

        binding.btnCertificationOff.setOnClickListener {
            binding.btnCertificationOff.visibility = View.GONE
            binding.btnCertificationOn.visibility = View.VISIBLE

            optionCheck = true
        }

        binding.btnCertificationOn.setOnClickListener {
            binding.btnCertificationOff.visibility = View.VISIBLE
            binding.btnCertificationOn.visibility = View.GONE

            optionCheck = false
        }

        //-----------------------------------------------------------------------

    }

    fun sendSmsMessage(mContext: Context, phone: String, message: String) {
        // 리턴 값 선언
        var returnData = false
        var m_log = ""
        var errorFlag = false

        try {
            // 1. input 값 null 체크
            if (phone.isEmpty()) {
                m_log = "[ERROR] : Input Data Is Null"
                errorFlag = true
            }

            // 2. permission 권한 부여 상태 확인
            if (ContextCompat.checkSelfPermission(
                    mContext!!,
                    Manifest.permission.SEND_SMS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
            } else { //
                m_log = "[ERROR] : [SEND_SMS] Permission Not Granted"
                errorFlag = true
            }

//            // 3. 휴대폰에 유심 장착됐는지 확인
//            if (C_StateCheck.getUsimMouting(mContext) === false) {
//                m_log = "[ERROR] : [SEND_SMS] Device Usim Not Mounting"
//                errorFlag = true
//            }

            // 4. SMS 문자 발송 수행
            if (errorFlag == false) {
                var sendIntent: PendingIntent? = null
                var deliveredIntent: PendingIntent? =null

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    // 타겟 31 이상
                    sendIntent = PendingIntent.getBroadcast(mContext, 0, Intent("SMS_SENT"), PendingIntent.FLAG_IMMUTABLE)
                    deliveredIntent = PendingIntent.getBroadcast(mContext, 0, Intent("SMS_DELIVERED"), PendingIntent.FLAG_IMMUTABLE)
                } else {
                    // 타겟 31 미만
                    sendIntent = PendingIntent.getBroadcast(mContext, 0, Intent("SMS_SENT"),
                        PendingIntent.FLAG_IMMUTABLE)
                    deliveredIntent = PendingIntent.getBroadcast(mContext, 0, Intent("SMS_DELIVERED"),
                        PendingIntent.FLAG_IMMUTABLE)
                }

                val smsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(phone, null, message, sendIntent, deliveredIntent)
                m_log = "[SUCESS] : SEND_SMS"
                returnData = true
            }
        } catch (e: Exception) {
            m_log = "[Exeption]: " + e.message.toString()
            errorFlag = true
        }

        Log.d("TEST인증번호오오오", m_log)

    }

    class SmsSentReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when (resultCode) {
                RESULT_OK -> {
                    // SMS 전송 성공 처리
                    Log.d("SmsSentReceiver", "SMS 전송 성공")
                }
                SmsManager.RESULT_ERROR_GENERIC_FAILURE -> {
                    // 일반적인 오류 처리
                    Log.d("SmsSentReceiver", "일반 오류 발생")
                }
                SmsManager.RESULT_ERROR_NO_SERVICE -> {
                    // 서비스 없음 오류 처리
                    Log.d("SmsSentReceiver", "서비스 없음")
                }
                SmsManager.RESULT_ERROR_NULL_PDU -> {
                    // PDU(null) 오류 처리
                    Log.d("SmsSentReceiver", "PDU(null) 오류")
                }
                SmsManager.RESULT_ERROR_RADIO_OFF -> {
                    // 무선(Radio) 꺼짐 오류 처리
                    Log.d("SmsSentReceiver", "무선(Radio) 꺼짐")
                }
            }
        }
    }
}
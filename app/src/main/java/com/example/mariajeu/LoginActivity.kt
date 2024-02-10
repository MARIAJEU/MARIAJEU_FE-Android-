package com.example.mariajeu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mariajeu.databinding.ActivityLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity(){

    lateinit var binding: ActivityLoginBinding
    var login: LoginDTO? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toLogin = intent.getStringExtra("로그인으로")

        // 카카오톡 로그인 -------------------------------------------------------

        val keyHash = Utility.getKeyHash(this)
        Log.e("Key", "keyHash: ${keyHash}")

        /** KakoSDK init */
        KakaoSdk.init(this, this.getString(R.string.kakao_app_key))

        // 서버 연동을 위한 세팅--------------------------------------------------
        var retrofit = Retrofit.Builder()
            .baseUrl("http://172.30.1.50:8000") //TODO 서버 주소 수정 필요
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var loginService: LoginService = retrofit.create(LoginService::class.java)

        //---------------------------------------------------------------------

        binding.loginSignInTv.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // 카카오톡 로그인
        binding.loginKakaoLoginBtn.setOnClickListener {
            kakaoLogin()
        }

        binding.loginLoginBtn.setOnClickListener {
            var userId = binding.loginIdEt.text.toString()
            var password = binding.loginPasswordEt.text.toString()

            loginService.requestLogin(userId, password).enqueue(object: Callback<LoginDTO> {
                override fun onFailure(call: Call<LoginDTO>, t: Throwable) {
                    Log.e("LOGIN FAILURE",t.message.toString())
                }

                override fun onResponse(call: Call<LoginDTO>, response: Response<LoginDTO>) {
                    login = response.body()
                    Log.d("LOGIN SUCCESS","msg : "+login?.msg)
                    Log.d("LOGIN SUCCESS","code : "+login?.code)
                }
            })
        }
    }

    private fun kakaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
//                TextMsg(this, "카카오계정으로 로그인 실패 : ${error}")
                setLogin(false)
            } else if (token != null) {
                //TODO: 최종적으로 카카오로그인 및 유저정보 가져온 결과
                UserApiClient.instance.me { user, error ->
//                    TextMsg(this, "카카오계정으로 로그인 성공 \n\n " +
//                            "token: ${token.accessToken} \n\n " +
//                            "me: ${user}")
                    setLogin(true)
                }
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
//                    TextMsg(this, "카카오톡으로 로그인 실패 : ${error}")

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
//                    TextMsg(this, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    setLogin(true)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun setLogin(bool: Boolean){
        val loginItent = Intent(this, StartFragment::class.java)
        loginItent.putExtra("로그인 성공", "success")
        Log.d("tag", "로그인 성공함")
        setResult(RESULT_OK, loginItent)
//        startActivity(loginItent)
        finish() // 이전 액티비티로 돌아가기
//        binding.btnStartKakaoLogout.visibility = if(bool) View.VISIBLE else View.GONE
//        binding.btnStartKakaoUnlink.visibility = if(bool) View.VISIBLE else View.GONE
    }
}
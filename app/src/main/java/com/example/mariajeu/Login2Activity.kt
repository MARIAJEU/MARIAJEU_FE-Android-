package com.example.mariajeu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mariajeu.databinding.ActivityLogin2Binding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date


/* TODO 서버 연동해서 프로필 사진 -> 서버에 전송하도록 해야 함 */
class Login2Activity : AppCompatActivity() {

    lateinit var binding: ActivityLogin2Binding
    private lateinit var uri: Uri
    private val client = RetrofitInstance.getInstance().create(ApiService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이미지뷰를 눌렀을 경우
        binding.ibProfile.setOnClickListener {
            // ACTION_PICK을 사용하여 앨범을 호출한다.
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            registerForActivityResult.launch(intent)
        }

        // 등록하기 버튼을 눌렀을 경우
        binding.ivRegister.setOnClickListener {
//            imageUpload(uri)
            // Activity -> Fragment 전환??

            // 서버 연결

            var userId = SignUpActivity.userId
            var password = SignUpActivity.password
            var name = SignUpActivity.username
            var emailAddr = SignUpActivity.emailAddr
            var phoneNum = SignUpActivity.phoneNum
            var nickname = binding.etNickname.text.toString()

            Log.d("회원가입테스투", emailAddr)

            var agreedToTerms1 = SignUpActivity.agreeCheck1
            var agreedToTerms2 = SignUpActivity.agreeCheck2
            var agreedToOptionalTerms = SignUpActivity.optionCheck

            Log.d("fdfdfdf",
                userId+password+name+emailAddr+phoneNum+nickname+agreedToTerms1+agreedToTerms2+agreedToOptionalTerms
            )

            var signUpBody = SignUpDTO(userId, password, name, emailAddr, "01011112222","111111",nickname, "ADMIN", agreedToTerms1, agreedToTerms2, agreedToOptionalTerms)
            CoroutineScope(Dispatchers.IO).launch {
                client.signup(signUpBody).enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.isSuccessful) {
                            Log.d("successful", response.body().toString())
                        } else {
                            Log.d("not successful", response.errorBody()?.string()!!)
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.d("[ Sign up ] connection failure", t.message.toString())
                    }

                })
            }

            val startFragment = supportFragmentManager.findFragmentById(R.id.start_fragment) as? StartFragment
            val mypageFragment = supportFragmentManager.findFragmentById(R.id.mypageFragment) as? MypageFragment

            startFragment?.startLogin()
            mypageFragment?.mypageLogin()

//            loginService.requestLogin(userId, password).enqueue(object: Callback<LoginDTO> {
//                override fun onFailure(call: Call<LoginDTO>, t: Throwable) {
//                    Log.e("LOGIN FAILURE",t.message.toString())
//                }
//
//                override fun onResponse(call: Call<LoginDTO>, response: Response<LoginDTO>) {
//                    login = response.body()
//                    Log.d("LOGIN SUCCESS","msg : "+login?.msg)
//                    Log.d("LOGIN SUCCESS","code : "+login?.code)
//                }
//            })
//
            val login2Intent = Intent(this, MainActivity::class.java)
            login2Intent.putExtra("로그아웃으로", "logout")
            startActivity(login2Intent)

        }

        // 체크 버튼 눌렀을 때 -> 이름으로 닉네임이 설정됨
        binding.btnCheckNameOff.setOnClickListener {
            binding.btnCheckNameOff.visibility = View.GONE
            binding.btnCheckNameOn.visibility = View.VISIBLE

            val userName = intent.getStringExtra("이름")
            Log.d("TEST전달TEST전달", userName.toString())
            binding.etNickname.setText(userName.toString())
        }

        binding.btnCheckNameOn.setOnClickListener {
            binding.btnCheckNameOff.visibility = View.VISIBLE
            binding.btnCheckNameOn.visibility = View.GONE

            val userName = intent.getStringExtra("이름")
            Log.d("TEST전달TEST전달", userName.toString())
            binding.etNickname.setText("")
        }

    }

    private val registerForActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                RESULT_OK -> {
                    // 변수 uri에 전달 받은 이미지 uri를 넣어준다.
                    uri = result.data?.data!!

                    // 이미지 업로드 글씨 사라지게 함
                    binding.tvUpload.visibility = View.GONE

                    // 이미지를 ImageView에 표시한다.
                    binding.ibProfile.setImageURI(uri)
                }
            }
        }


    // TODO 이미지 업로드가 안 되고 런타임에러 떠서 수정 필요 ** (서버 연동하면 어차피 다른 방식으로 할 거 같긴 함)
    private fun imageUpload(uri: Uri) {
        // storage 인스턴스 생성
        val storage = Firebase.storage
        // storage 참조
        val storageRef = storage.getReference("image")
        // storage에 저장할 파일명 선언
        val fileName = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val mountainsRef = storageRef.child("${fileName}.png")

        val uploadTask = mountainsRef.putFile(uri)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            // 파일 업로드 성공
            Toast.makeText(this, "사진 업로드 성공", Toast.LENGTH_SHORT).show();
        }.addOnFailureListener {
            // 파일 업로드 실패
            Toast.makeText(this, "사진 업로드 실패", Toast.LENGTH_SHORT).show();
        }
    }

}

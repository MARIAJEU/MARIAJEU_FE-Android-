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
import java.text.SimpleDateFormat
import java.util.Date


/* TODO 서버 연동해서 프로필 사진 -> 서버에 전송하도록 해야 함 */
class Login2Activity : AppCompatActivity() {

    lateinit var binding: ActivityLogin2Binding
    private lateinit var uri: Uri

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
            val login2Intent = Intent(this, MainActivity::class.java)
            login2Intent.putExtra("로그아웃으로", "logout")
            startActivity(login2Intent)

        }

        // 체크 버튼 눌렀을 때 -> 이름으로 닉네임이 설정됨
        binding.btnCheckName.setOnClickListener {
            val userName = intent.getStringExtra("이름")
            Log.d("TEST전달TEST전달", userName.toString())
            binding.etNickname.setText(userName.toString())
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
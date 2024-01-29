package com.example.mariajeu

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import com.example.mariajeu.databinding.DialogReservationBinding

class ReservationDialog(context: Context) : Dialog(context) {
    lateinit var binding: DialogReservationBinding

    companion object {
        lateinit var btnGoback: ImageButton
        lateinit var btnMyPage: ImageButton
        lateinit var rName: TextView
        lateinit var rDate: TextView
        lateinit var rTime: TextView
        lateinit var rCnt: TextView
    }

    init {
        // 클래스 초기화 시에 초기화 블록(init)을 사용하여 변수 초기화
        binding = DialogReservationBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.dialog_reservation)

        // 커스텀 다이얼로그 레이아웃의 UI 요소에 대한 이벤트 처리
        btnGoback = findViewById(R.id.dialog_button_goback)
        btnMyPage = findViewById(R.id.dialog_button_mypage)

        rName = findViewById(R.id.tv_dialog_name)
        rDate = findViewById(R.id.tv_dialog_date)
        rTime = findViewById(R.id.tv_dialog_time)
        rCnt = findViewById(R.id.tv_dialog_cnt_personnel)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding.dialogButtonGoback.setOnClickListener {
            Log.d("ReservationDialog", "btnGoback clicked")
            dismiss()
        }
    }
}

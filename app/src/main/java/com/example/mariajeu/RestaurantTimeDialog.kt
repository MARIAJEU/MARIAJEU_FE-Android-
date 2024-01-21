package com.example.mariajeu
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class RestaurantTimeDialog(context: Context, private val listener: RestaurantTimeDialogInterface) : Dialog(context) {

    companion object {
        lateinit var rName: TextView
        lateinit var rDate: TextView
        lateinit var rTime: TextView
        lateinit var rCnt: TextView
    }

    init {
        // 클래스 초기화 시에 초기화 블록(init)을 사용하여 변수 초기화
        setContentView(R.layout.dialog_restaurant_time)

        // 커스텀 다이얼로그 레이아웃의 UI 요소에 대한 이벤트 처리
        val confirmButton: ImageButton = findViewById(R.id.dialog_button_confirm)
        val cancelButton: ImageButton = findViewById(R.id.dialog_button_cancel)

        rName = findViewById(R.id.tv_dialog_name)
        rDate = findViewById(R.id.tv_dialog_date)
        rTime = findViewById(R.id.tv_dialog_time)
        rCnt = findViewById(R.id.tv_dialog_cnt_personnel)

        confirmButton.setOnClickListener {
            listener.onYesButtonClick(1) // 예 버튼 클릭 시 이벤트 처리
            dismiss()
        }

        cancelButton.setOnClickListener {
            listener.onCancelButtonClick() // 취소 버튼 클릭 시 이벤트 처리
            dismiss()
        }
    }
}

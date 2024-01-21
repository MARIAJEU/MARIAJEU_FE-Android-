package com.example.mariajeu
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageButton

class RestaurantTimeDialog(context: Context, private val listener: RestaurantTimeDialogInterface) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_restaurant_time)

        // 커스텀 다이얼로그 레이아웃의 UI 요소에 대한 이벤트 처리
        val confirmButton: ImageButton = findViewById(R.id.dialog_button_confirm)
        val cancelButton: ImageButton = findViewById(R.id.dialog_button_cancel)

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
package com.example.mariajeu
// RestaurantAdapterViewModel.kt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mariajeu.Restaurant

class RestaurantAdapterViewModel : ViewModel() {
    // LiveData를 사용하여 버튼 클릭 상태 관리
    private val _buttonStates = MutableLiveData<MutableMap<Int, Boolean>>()
    val buttonStates: LiveData<MutableMap<Int, Boolean>> = _buttonStates

    init {
        _buttonStates.value = mutableMapOf() // 초기화
    }

    fun setButtonState(position: Int, isClicked: Boolean) {
        val currentState = _buttonStates.value ?: mutableMapOf()
        currentState[position] = isClicked
        _buttonStates.value = currentState
    }
}
package com.example.mariajeu

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "700c1077d57196cc26390d25f4662d35")
    }
}
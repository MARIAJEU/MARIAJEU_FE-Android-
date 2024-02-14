package com.example.mariajeu

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerConnection {

    companion object {
        private const val BASE_URL = "http://43.202.194.137:8080/"

        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}
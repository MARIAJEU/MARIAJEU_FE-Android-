package com.example.mariajeu

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/users/join")
    fun signup(
        @Body userData: SignUpDTO
    ): Call<ResponseBody>

    @POST("/auth/login")
    fun login(
        @Body userData: LoginDTO
    ): Call<ResponseBody>
}
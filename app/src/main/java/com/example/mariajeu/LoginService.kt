package com.example.mariajeu

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService{

    @FormUrlEncoded
    @POST("/users/login")
    fun requestLogin(
        @Field("userName") userName: String,
        @Field("password") password: String
    ) : Call<LoginDTO>

}
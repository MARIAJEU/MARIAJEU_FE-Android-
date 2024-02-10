package com.example.mariajeu

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpService {
    @FormUrlEncoded
    @POST("/users/join")
    fun requestSignUp(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("nickName") nickName: String,
        @Field("agreedToTerms1") agreedToTerms1: Boolean,
        @Field("agreedToTerms2") agreedToTerms2: Boolean,
        @Field("agreedToOptionalTerms") agreedToOptionalTerms: Boolean,

    ) : Call<SignUpDTO>
}
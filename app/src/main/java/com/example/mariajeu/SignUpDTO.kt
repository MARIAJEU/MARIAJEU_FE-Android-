package com.example.mariajeu

import retrofit2.http.Field

data class SignUpDTO(
    val userName: String,
    val password: String,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val authNum: String,
    val nickName: String,
    val role: String,
    val agreedToTerms1: Boolean,
    val agreedToTerms2: Boolean,
    val agreedToOptionalTerms: Boolean
)

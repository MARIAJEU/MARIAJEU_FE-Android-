package com.example.mariajeu

import retrofit2.http.Field

data class SignUpDTO(
    val username: String,
    val password: String,
    val name: String,
    val email: String,
    val nickName: String,
    val agreedToTerms1: Boolean,
    val agreedToTerms2: Boolean,
    val agreedToOptionalTerms: Boolean
)

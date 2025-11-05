package com.example.parcial_pr3_ort.data.dto

data class CreateAccountRequest(
    val fullName: String,
    val email: String,
    val mobile: String,
    val dob: String,
    val password: String
)
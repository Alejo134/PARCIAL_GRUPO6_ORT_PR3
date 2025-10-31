package com.example.parcial_pr3_ort.data.dao

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("address")
    val address: Address, // <-- Objeto anidado

    @SerializedName("id")
    val id: Int,

    @SerializedName("email")
    val email: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String,

    @SerializedName("name")
    val name: UserName, // <-- Objeto anidado

    @SerializedName("phone")
    val phone: String


)

package com.example.parcial_pr3_ort.data.model

import com.google.gson.annotations.SerializedName

data class UserName(
    @SerializedName("firstname")
    val firstname: String,

    @SerializedName("lastname")
    val lastname: String
)

package com.example.parcial_pr3_ort.data.model

import com.google.gson.annotations.SerializedName

data class Geolocation(
    @SerializedName("lat")
    val lat: String,

    @SerializedName("long")
    val long: String
)

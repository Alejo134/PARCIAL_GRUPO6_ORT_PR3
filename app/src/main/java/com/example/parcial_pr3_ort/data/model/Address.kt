package com.example.parcial_pr3_ort.data.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("geolocation")
    val geolocation: Geolocation, // <-- Objeto anidado

    @SerializedName("city")
    val city: String,

    @SerializedName("street")
    val street: String,

    @SerializedName("number")
    val number: Int,

    @SerializedName("zipcode")
    val zipcode: String
)

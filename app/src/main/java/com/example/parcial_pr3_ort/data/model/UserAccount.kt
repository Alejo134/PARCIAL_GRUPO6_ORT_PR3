package com.example.parcial_pr3_ort.data.model

import com.google.gson.annotations.SerializedName

data class UserAccount(
    @SerializedName("user_id")
    val userId: Int,

    @SerializedName("balance")
    val balance: Double,

    @SerializedName("income")
    val income: Double,

    @SerializedName("expense")
    val expense: Double,

    // lista que contiene objetos de otra data class
    @SerializedName("transactions")
    val transactions: List<Transaction>
)

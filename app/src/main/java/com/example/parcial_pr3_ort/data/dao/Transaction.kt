package com.example.parcial_pr3_ort.data.dao

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("transaction_id")
    val transactionId: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("amount")
    val amount: Double,

    @SerializedName("currency")
    val currency: String,

    @SerializedName("type")
    val type: String,

    @SerializedName("subtype")
    val subtype: String
)

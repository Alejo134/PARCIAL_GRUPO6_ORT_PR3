package com.example.parcial_pr3_ort.data.model.profile

import com.google.gson.annotations.SerializedName

data class UserProfile(
    @SerializedName("user_id") val userId: String,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("balance") val balance: Double,
    @SerializedName("credit_card") val creditCard: ProfileCreditCard,
    @SerializedName("bank_account") val bankAccount: ProfileBankAccount,
    @SerializedName("transactions") val transactions: ProfileTransactions
)

data class ProfileCreditCard(
    @SerializedName("card_number") val cardNumber: String,
    @SerializedName("cardholder_name") val cardholderName: String,
    @SerializedName("expiration_date") val expirationDate: String,
    @SerializedName("cvv") val cvv: String,
    @SerializedName("credit_limit") val creditLimit: Double,
    @SerializedName("current_balance") val currentBalance: Double,
    @SerializedName("available_balance") val availableBalance: Double
)

data class ProfileBankAccount(
    @SerializedName("bank_name") val bankName: String,
    @SerializedName("account_type") val accountType: String,
    @SerializedName("cvu") val cvu: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("currency") val currency: String
)

data class ProfileTransactions(
    @SerializedName("credit_card_transactions") val creditCardTransactions: List<ProfileTxItem>,
    @SerializedName("bank_account_transactions") val bankAccountTransactions: List<ProfileTxItem>
)

data class ProfileTxItem(
    @SerializedName("transaction_id") val transactionId: String,
    @SerializedName("date") val date: String,
    @SerializedName("description") val description: String,
    @SerializedName("amount") val amount: Double,
    @SerializedName("currency") val currency: String,
    @SerializedName("type") val type: String // "debit" | "credit"
)

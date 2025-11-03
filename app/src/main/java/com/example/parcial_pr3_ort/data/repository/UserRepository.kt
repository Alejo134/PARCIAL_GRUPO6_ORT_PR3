package com.example.parcial_pr3_ort.data.repository

import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.model.profile.UserProfile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    suspend fun getUserById(userId: Int): Result<UserProfile> = withContext(Dispatchers.IO) {
        try {
            val res = RetrofitClient.api.getUserByIdProfile(userId)
            if (res.isSuccessful && res.body() != null) {
                Result.success(res.body()!!)
            } else {
                Result.failure(IllegalStateException("HTTP ${res.code()} ${res.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

package com.example.parcial_pr3_ort.data.repository

import com.example.parcial_pr3_ort.api.ApiService
import com.example.parcial_pr3_ort.data.model.UserDetail

class ProfileRepository(private val apiService: ApiService) {

    /**
     * Obtiene los datos del usuario por ID.
     * Devuelve Result<UserDetail> para manejar éxito o error.
     */
    suspend fun getUserById(userId: Int): Result<UserDetail> {
        return try {
            val response = apiService.getUserById(userId)

            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Error en la respuesta del servidor: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

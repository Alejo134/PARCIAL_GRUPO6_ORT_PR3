package com.example.parcial_pr3_ort.data.repository

import com.example.parcial_pr3_ort.api.ApiService
import com.example.parcial_pr3_ort.data.model.UserAccount

class UserAccountRepository(private val apiService: ApiService) {

    /**
     * Obtiene los datos de UserAccount.
     * Esta función ahora devuelve Result<UserAccount> directamente.
     * Se encarga de procesar la respuesta de Retrofit.
     */
    suspend fun getUserAccountData(): Result<UserAccount> {
        return try {
            // 1. Llamamos a la API
            val response = apiService.getUserAccount()

            // 2. Verificamos si la respuesta fue exitosa y tiene cuerpo
            if (response.isSuccessful && response.body() != null) {
                // 3. Si todo está bien, devolvemos SOLO el cuerpo (el UserAccount)
                Result.success(response.body()!!)
            } else {
                // 4. Si hay un error de servidor (ej: 404, 500), creamos un error personalizado
                Result.failure(Exception("Error en la respuesta del servidor: ${response.code()}"))
            }
        } catch (e: Exception) {
            // 5. Si hay un error de red (ej: sin internet), lo capturamos
            Result.failure(e)
        }
    }
}

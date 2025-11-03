package com.example.parcial_pr3_ort.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.dto.LoginRequest
import com.example.parcial_pr3_ort.ui.screens.LoginUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<LoginUIState>(LoginUIState.Idle)
    val uiState = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        if (_uiState.value is LoginUIState.Loading) return

        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUIState.Error("Email y contraseña no pueden estar vacíos")
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginUIState.Loading
            try {
                val request = LoginRequest(email, password)
                val response = RetrofitClient.api.login(request)

                if (response.isSuccessful) {
                    val loginData = response.body()
                    if (loginData != null) {
                        _uiState.value = LoginUIState.Success(loginData)
                    } else {
                        _uiState.value = LoginUIState.Error("Respuesta vacía")
                    }
                } else {
                    _uiState.value = LoginUIState.Error("Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _uiState.value = LoginUIState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun resetState() {
        _uiState.value = LoginUIState.Idle
    }
}
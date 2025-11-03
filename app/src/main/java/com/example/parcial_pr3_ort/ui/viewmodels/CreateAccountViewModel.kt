package com.example.parcial_pr3_ort.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.data.dto.CreateAccountRequest
import com.example.parcial_pr3_ort.ui.screens.CreateAccountUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreateAccountViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<CreateAccountUIState>(CreateAccountUIState.Idle)
    val uiState = _uiState.asStateFlow()

    fun createAccount(
        fullName: String,
        email: String,
        mobile: String,
        dob: String,
        password: String
    ) {
        if (_uiState.value is CreateAccountUIState.Loading) return

        if (fullName.isBlank() || email.isBlank() || password.isBlank()) {
            _uiState.value = CreateAccountUIState.Error("Completá todos los campos")
            return
        }

        viewModelScope.launch {
            _uiState.value = CreateAccountUIState.Loading
            try {
                val request = CreateAccountRequest(fullName, email, mobile, dob, password)
                val response = RetrofitClient.api.createUser(request)

                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        _uiState.value = CreateAccountUIState.Success(user)
                    } else {
                        _uiState.value = CreateAccountUIState.Error("Respuesta vacía")
                    }
                } else {
                    _uiState.value = CreateAccountUIState.Error("Error: ${response.code()} ${response.message()}")
                }
            } catch (e: Exception) {
                _uiState.value = CreateAccountUIState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    fun resetState() {
        _uiState.value = CreateAccountUIState.Idle
    }
}
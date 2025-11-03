package com.example.parcial_pr3_ort.ui.screens

import com.example.parcial_pr3_ort.data.dao.LoginResponse

sealed interface LoginUIState {
    object Idle : LoginUIState
    object Loading : LoginUIState
    data class Success(val response: LoginResponse) : LoginUIState
    data class Error(val message: String) : LoginUIState
}
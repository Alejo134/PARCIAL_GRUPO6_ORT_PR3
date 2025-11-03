package com.example.parcial_pr3_ort.ui.screens

import com.example.parcial_pr3_ort.data.dao.UserDetail

sealed interface CreateAccountUIState {
    object Idle : CreateAccountUIState
    object Loading : CreateAccountUIState
    data class Success(val user: UserDetail) : CreateAccountUIState
    data class Error(val message: String) : CreateAccountUIState
}
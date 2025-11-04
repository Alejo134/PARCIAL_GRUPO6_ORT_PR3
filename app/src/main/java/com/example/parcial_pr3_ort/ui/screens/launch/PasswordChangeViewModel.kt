package com.example.parcial_pr3_ort.ui.screens.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PasswordChangeViewModel : ViewModel() {
    private val _state = MutableStateFlow<PasswordChangeState>(PasswordChangeState.LoadingCheck1)
    val state = _state.asStateFlow()

    /**
     * Inicia el proceso de cambio de contraseña y la animación.
     * En un caso real, recibiría la contraseña y llamaría a una API.
     */
    fun startChangeProcess(success: Boolean) {
        viewModelScope.launch {

            _state.value = PasswordChangeState.LoadingCheck1
            delay(500) // Pausa de 0.5 segundos
            _state.value = PasswordChangeState.LoadingCheck2
            delay(500)
            _state.value = PasswordChangeState.LoadingCheck3
            delay(500)

            if (success) {
                _state.value = PasswordChangeState.Success
            } else {
                _state.value = PasswordChangeState.LoadingDenied1
                delay(500)
                _state.value = PasswordChangeState.LoadingDenied2
                delay(500)
                _state.value = PasswordChangeState.LoadingDenied3
                delay(500)
                _state.value = PasswordChangeState.Error
            }
        }
    }
}
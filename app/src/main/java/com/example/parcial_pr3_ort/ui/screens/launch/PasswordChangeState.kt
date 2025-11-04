package com.example.parcial_pr3_ort.ui.screens.launch
    /**
     * Define los estados visuales para la pantalla de cambio de contraseña.
     * Refleja las secuencias de animación de 'check' y 'denied'.
     */
    sealed interface PasswordChangeState {
        object LoadingCheck1 : PasswordChangeState
        object LoadingCheck2 : PasswordChangeState
        object LoadingCheck3 : PasswordChangeState
        object Success : PasswordChangeState // check_complete

        object LoadingDenied1 : PasswordChangeState
        object LoadingDenied2 : PasswordChangeState
        object LoadingDenied3 : PasswordChangeState
        object Error : PasswordChangeState
    }

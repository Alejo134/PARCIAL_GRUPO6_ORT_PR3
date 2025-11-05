package com.example.parcial_pr3_ort.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parcial_pr3_ort.data.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val _userName = mutableStateOf("")
    val userName: State<String> = _userName

    private val _userId = mutableStateOf("")
    val userId: State<String> = _userId

    private val _userEmail = mutableStateOf("")
    val userEmail: State<String> = _userEmail

    private val _userPhone = mutableStateOf("")
    val userPhone: State<String> = _userPhone

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    init {
        // Por ahora usamos un ID hardcodeado, luego se puede obtener del login
        fetchUserProfile(1)
    }

    private fun fetchUserProfile(userId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.getUserById(userId)

            result.onSuccess { userDetail ->
                _userName.value = "${userDetail.name.firstname} ${userDetail.name.lastname}"
                _userId.value = userDetail.id.toString()
                _userEmail.value = userDetail.email
                _userPhone.value = userDetail.phone
            }.onFailure { exception ->
                _errorMessage.value = "Error al cargar el perfil: ${exception.message}"
            }

            _isLoading.value = false
        }
    }
}

class ProfileViewModelFactory(private val repository: ProfileRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

package com.example.parcial_pr3_ort.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parcial_pr3_ort.data.model.Transaction
import com.example.parcial_pr3_ort.data.repository.UserAccountRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: UserAccountRepository) : ViewModel() {

    // --- ESTADOS PARA LA UI ---
    // Un estado privado mutable y uno público inmutable para cada dato que necesita la UI.

    private val _totalBalance = mutableStateOf(0.0)
    val totalBalance: State<Double> = _totalBalance

    private val _totalExpense = mutableStateOf(0.0)
    val totalExpense: State<Double> = _totalExpense

    private val _transactions = mutableStateOf<List<Transaction>>(emptyList())
    val transactions: State<List<Transaction>> = _transactions

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage


    // El bloque init se ejecuta una vez cuando el ViewModel es creado.
    init {
        fetchUserAccount()
    }

    private fun fetchUserAccount() {
        // Usamos el scope del ViewModel para que la corrutina se cancele si el ViewModel se destruye.
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val result = repository.getUserAccountData()

            // Usamos las funciones de extensión de Result para manejar éxito o fallo.
            result.onSuccess { userAccount ->
                // Si la llamada a la API fue exitosa, actualizamos los estados con los datos recibidos.
                _totalBalance.value = userAccount.balance
                _totalExpense.value = userAccount.expense
                _transactions.value = userAccount.transactions
            }.onFailure { exception ->

                _errorMessage.value = "Error al cargar los datos: ${exception.message}"
            }

            _isLoading.value = false
        }
    }
}


// Fábrica necesaria para poder inyectar el 'UserAccountRepository' en el constructor del ViewModel.
class HomeViewModelFactory(private val repository: UserAccountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

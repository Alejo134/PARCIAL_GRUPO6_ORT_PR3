package com.example.parcial_pr3_ort.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.parcial_pr3_ort.data.TransactionRepository
import com.example.parcial_pr3_ort.data.entity.EntityTransaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CategoryDetailViewModel(
    private val repository: TransactionRepository,
    private val categoryName: String
) : ViewModel() {

    private val _groupedTransactions = MutableStateFlow<Map<String, List<EntityTransaction>>>(emptyMap())
    val groupedTransactions: StateFlow<Map<String, List<EntityTransaction>>> = _groupedTransactions.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getTransactionsByCategory(categoryName).collect { transactions ->
                _groupedTransactions.value = transactions.groupBy { transaction ->
                    formatDateToMonthYear(transaction.date)
                }
            }
        }
    }

    private fun formatDateToMonthYear(timestamp: Long): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calendar.time).uppercase()
    }
}

/**
 * Factory obligatoria para crear un ViewModel con parámetros.
 */
class CategoryDetailViewModelFactory(
    private val repository: TransactionRepository,
    private val categoryName: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CategoryDetailViewModel(repository, categoryName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

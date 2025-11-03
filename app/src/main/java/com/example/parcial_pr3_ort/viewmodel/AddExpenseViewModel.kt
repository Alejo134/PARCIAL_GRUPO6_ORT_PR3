package com.example.parcial_pr3_ort.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial_pr3_ort.data.TransactionRepository
import com.example.parcial_pr3_ort.data.dao.TransactionDao
import com.example.parcial_pr3_ort.data.entity.EntityTransaction
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModelProvider


class AddExpenseViewModel(private val transactionRepository: TransactionRepository) : ViewModel() {

    fun saveTransaction(
        title: String,
        amount: Double,
        date: Long,
        category: String,
        message: String
    ) {
        viewModelScope.launch {
            val newTransaction = EntityTransaction(
                title = title,
                category = category,
                date = date,
                amount = amount,
                message = message
            )
            transactionRepository.insertTransaction(newTransaction)
        }
    }
}


class AddExpenseViewModelFactory(private val repository: TransactionRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddExpenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


package com.example.parcial_pr3_ort.data

import com.example.parcial_pr3_ort.data.dao.TransactionDao
import com.example.parcial_pr3_ort.data.entity.EntityTransaction
import kotlinx.coroutines.flow.Flow

/**
 * Repositorio que actúa como intermediario entre los ViewModels y TransactionDao.
 */
class TransactionRepository(private val transactionDao: TransactionDao) {

    /**
     * Obtiene un Flow con todas las transacciones de una categoría específica.
     * @param categoryName El nombre de la categoría a filtrar (ej. "Food", "Rent").
     */
    fun getTransactionsByCategory(categoryName: String): Flow<List<EntityTransaction>> {
        // Llama a la nueva función que acabamos de añadir en el DAO
        return transactionDao.getTransactionsByCategory(categoryName)
    }

    suspend fun insertTransaction(transaction: EntityTransaction) {
        transactionDao.insertTransaction(transaction)
    }
}

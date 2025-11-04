package com.example.parcial_pr3_ort.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.parcial_pr3_ort.data.entity.EntityTransaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    // Inserta una nueva transacción.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: EntityTransaction)

    // Obtiene TODAS las transacciones, ordenadas por fecha (las más nuevas primero).
    // Devuelve un Flow para actualizaciones automáticas en la UI.
    @Query("SELECT * FROM `transaction` ORDER BY date DESC")
    fun getAllTransactions(): Flow<List<EntityTransaction>>

    // Obtiene una única transacción por su ID.
    @Query("SELECT * FROM `transaction` WHERE id = :id")
    suspend fun getTransactionById(id: Int): EntityTransaction?

    // Borra todas las transacciones de la tabla.
    @Query("DELETE FROM `transaction`") // REFACTOR 3: La tabla se llama 'transaction'
    suspend fun deleteAllTransactions()


    @Query("SELECT * FROM `transaction` WHERE category = :categoryName ORDER BY date DESC")
    fun getTransactionsByCategory(categoryName: String): Flow<List<EntityTransaction>>
}

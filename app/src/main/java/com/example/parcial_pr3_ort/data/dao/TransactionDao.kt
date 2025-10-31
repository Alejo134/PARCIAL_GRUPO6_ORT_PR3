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
    suspend fun insertTransaction(transaction: EntityTransaction) // REFACTOR 2: El método y el parámetro usan "Transaction"

    // Obtiene TODAS las transacciones, ordenadas por fecha (las más nuevas primero).
    // Devuelve un Flow para actualizaciones automáticas en la UI.
    @Query("SELECT * FROM `transaction` ORDER BY date DESC") // REFACTOR 3: La tabla se llama 'transaction'
    fun getAllTransactions(): Flow<List<EntityTransaction>> // REFACTOR 4: El método ahora es getAllTransactions

    // Obtiene una única transacción por su ID.
    @Query("SELECT * FROM `transaction` WHERE id = :id") // REFACTOR 3: La tabla se llama 'transaction'
    suspend fun getTransactionById(id: Int): EntityTransaction? // REFACTOR 5: El método ahora es getTransactionById

    // Borra todas las transacciones de la tabla.
    @Query("DELETE FROM `transaction`") // REFACTOR 3: La tabla se llama 'transaction'
    suspend fun deleteAllTransactions() // REFACTOR 6: El método ahora es deleteAllTransactions
}

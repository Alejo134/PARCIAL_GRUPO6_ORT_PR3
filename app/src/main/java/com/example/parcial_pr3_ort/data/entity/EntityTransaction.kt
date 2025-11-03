package com.example.parcial_pr3_ort.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction") // Nombre de la tabla en la base de datos
data class EntityTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Clave primaria autoincremental

    val title: String,
    val category: String,
    val date: Long, // Es mejor guardar fechas como Long (timestamp)
    val amount: Double,
    val message: String
)

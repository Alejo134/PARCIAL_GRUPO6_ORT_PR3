package com.example.parcial_pr3_ort.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction") // Nombre de la tabla en la base de datos
data class EntityTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Clave primaria autoincremental

    val category: String,
    val description: String,
    val amount: Double,
    val date: Long, // Es mejor guardar fechas como Long (timestamp)
    val expenseType: String, // "Monthly", "Rent", etc. para el expenseTitle
    val iconResName: String // Guardamos el NOMBRE del drawable, no el ID
)

package com.example.parcial_pr3_ort.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.parcial_pr3_ort.data.dao.TransactionDao
import com.example.parcial_pr3_ort.data.entity.EntityTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [EntityTransaction::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "transaction_database"
                )
                    .fallbackToDestructiveMigration()
                    // --- PASO 4: AÑADIR EL CALLBACK AQUÍ ---
                    .addCallback(DatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // --- PASO 4.1: CREAR LA CLASE DEL CALLBACK ---
        private class DatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {

            // Este método se llama UNA SOLA VEZ, cuando la base de datos se crea por primera vez.
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    // Usamos una corrutina para insertar los datos en un hilo de fondo.
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.transactionDao())
                    }
                }
            }

            suspend fun populateDatabase(transactionDao: TransactionDao) {
                // Limpiamos la base de datos por si acaso (aunque en onCreate no es necesario).
                transactionDao.deleteAllTransactions()

                // Creamos los datos de ejemplo
                val transaction1 = EntityTransaction(
                    category = "Salario",
                    description = "Pago Mensual",
                    amount = 4000.00,
                    date = System.currentTimeMillis() - 86400000L * 5, // Hace 5 días
                    expenseType = "Ingreso",
                    iconResName = "ic_salary" // Nombre del drawable
                )

                val transaction2 = EntityTransaction(
                    category = "Comida",
                    description = "Supermercado",
                    amount = -150.75,
                    date = System.currentTimeMillis() - 86400000L * 2, // Hace 2 días
                    expenseType = "Gasto",
                    iconResName = "ic_food" // Nombre del drawable
                )

                val transaction3 = EntityTransaction(
                    category = "Transporte",
                    description = "Gasolina",
                    amount = -50.00,
                    date = System.currentTimeMillis() - 86400000L * 1, // Ayer
                    expenseType = "Gasto",
                    iconResName = "ic_transport" // Asume que tienes un ic_transport.xml
                )

                val transaction4 = EntityTransaction(
                    category = "Ocio",
                    description = "Cine",
                    amount = -25.50,
                    date = System.currentTimeMillis(), // Hoy
                    expenseType = "Gasto",
                    iconResName = "ic_entertainment" // Asume que tienes este drawable
                )

                // Insertamos los datos en la base de datos
                transactionDao.insertTransaction(transaction1)
                transactionDao.insertTransaction(transaction2)
                transactionDao.insertTransaction(transaction3)
                transactionDao.insertTransaction(transaction4)
            }
        }
    }
}

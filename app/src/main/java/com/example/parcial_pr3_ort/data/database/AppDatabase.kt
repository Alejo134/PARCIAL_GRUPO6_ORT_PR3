package com.example.parcial_pr3_ort.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase // <-- 1. IMPORTA ESTO
import com.example.parcial_pr3_ort.data.dao.TransactionDao
import com.example.parcial_pr3_ort.data.entity.EntityTransaction
import kotlinx.coroutines.CoroutineScope // <-- 2. IMPORTA ESTO
import kotlinx.coroutines.Dispatchers // <-- 3. IMPORTA ESTO
import kotlinx.coroutines.launch // <-- 4. IMPORTA ESTO

@Database(entities = [EntityTransaction::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "financial_database"
                )
                    // --- 5. AÑADIMOS EL CALLBACK AQUÍ ---
                    .addCallback(DatabaseCallback(context))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    /**
     * Callback que se ejecuta cuando la base de datos es creada.
     * Lo usaremos para poblar la base de datos con datos de prueba.
     */
    private class DatabaseCallback(
        private val context: Context
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            // Usamos una corrutina para insertar los datos en un hilo de fondo.
            CoroutineScope(Dispatchers.IO).launch {
                // Obtenemos la instancia de la base de datos recién creada
                val database = getDatabase(context)
                // Insertamos los datos de prueba
                populateDatabase(database.transactionDao())
            }
        }

        suspend fun populateDatabase(transactionDao: TransactionDao) {
            // Borra todo lo anterior (opcional, pero bueno para pruebas)
            transactionDao.deleteAllTransactions()

            // --- 6. LISTA DE TRANSACCIONES DE PRUEBA ---
            val transactions = listOf(
                EntityTransaction(title = "Café en Starbucks", category = "Food", date = System.currentTimeMillis() - 1 * 86400000L, amount = 1.56, message = "Café y medialuna"),
                EntityTransaction(title = "Alquiler Octubre", category = "Rent", date = System.currentTimeMillis() - 5 * 86400000L, amount = -2.27, message = "Pago mensual"),
                EntityTransaction(title = "Supermercado", category = "Groceries", date = System.currentTimeMillis() - 10 * 86400000L, amount = -32.00, message = "Compra para la semana"),
                EntityTransaction(title = "Regalo para Ana", category = "Gifts", date = System.currentTimeMillis() - 15 * 86400000L, amount = 42.67, message = "Cumpleaños"),
                EntityTransaction(title = "Cena en Pizzería", category = "Food", date = System.currentTimeMillis() - 20 * 86400000L, amount = -42.75, message = "Salida con amigos"),
                EntityTransaction(title = "Netflix", category = "Entertainment", date = System.currentTimeMillis() - 35 * 86400000L, amount = -25.01, message = "Cargo mensual Septiembre"),
                EntityTransaction(title = "Pasaje de bus", category = "Transport", date = System.currentTimeMillis() - 40 * 86400000L, amount = 42.23, message = "Viaje al trabajo"),
                EntityTransaction(title = "Desayuno en McDonald's", category = "Food", date = System.currentTimeMillis() - 2 * 86400000L, amount = -4.90, message = "Combo café + tostado"),
                EntityTransaction(title = "Pago de Internet", category = "Rent", date = System.currentTimeMillis() - 6 * 86400000L, amount = -8.00, message = "Factura mensual de fibra"),
                EntityTransaction(title = "Compra de verduras", category = "Groceries", date = System.currentTimeMillis() - 11 * 86400000L, amount = -6.40, message = "Verdulería del barrio"),
                EntityTransaction(title = "Regalo para mamá", category = "Gifts", date = System.currentTimeMillis() - 18 * 86400000L, amount = -25.50, message = "Pulsera artesanal"),
                EntityTransaction(title = "Cena en Burger King", category = "Food", date = System.currentTimeMillis() - 21 * 86400000L, amount = -12.30, message = "Combo doble con bebida"),
                EntityTransaction(title = "Spotify Premium", category = "Entertainment", date = System.currentTimeMillis() - 36 * 86400000L, amount = -9.99, message = "Suscripción mensual"),
                EntityTransaction(title = "Taxi al aeropuerto", category = "Transport", date = System.currentTimeMillis() - 41 * 86400000L, amount = -18.60, message = "Viaje de madrugada"),
                EntityTransaction(title = "Cena con familia", category = "Food", date = System.currentTimeMillis() - 3 * 86400000L, amount = -22.45, message = "Restaurante local"),
                EntityTransaction(title = "Pago de luz", category = "Rent", date = System.currentTimeMillis() - 7 * 86400000L, amount = -14.20, message = "Factura mensual octubre"),
                EntityTransaction(title = "Compra en carnicería", category = "Groceries", date = System.currentTimeMillis() - 12 * 86400000L, amount = -18.00, message = "Carne para la semana"),
                EntityTransaction(title = "Regalo para amigo", category = "Gifts", date = System.currentTimeMillis() - 16 * 86400000L, amount = -10.00, message = "Caja de bombones"),
                EntityTransaction(title = "Almuerzo en oficina", category = "Food", date = System.currentTimeMillis() - 22 * 86400000L, amount = -7.85, message = "Milanesa con puré"),
                EntityTransaction(title = "Disney+", category = "Entertainment", date = System.currentTimeMillis() - 37 * 86400000L, amount = -11.99, message = "Suscripción mensual"),
                EntityTransaction(title = "Subte ida y vuelta", category = "Transport", date = System.currentTimeMillis() - 42 * 86400000L, amount = -2.20, message = "Viaje al centro"),
                EntityTransaction(title = "Café con compañeros", category = "Food", date = System.currentTimeMillis() - 4 * 86400000L, amount = -3.60, message = "Café y medialunas"),
                EntityTransaction(title = "Expensas del mes", category = "Rent", date = System.currentTimeMillis() - 8 * 86400000L, amount = -30.00, message = "Gastos del edificio"),
                EntityTransaction(title = "Compra en panadería", category = "Groceries", date = System.currentTimeMillis() - 13 * 86400000L, amount = -5.50, message = "Pan y facturas"),
                EntityTransaction(title = "Regalo sorpresa", category = "Gifts", date = System.currentTimeMillis() - 17 * 86400000L, amount = -18.30, message = "Flores y tarjeta"),
                EntityTransaction(title = "Cena sushi", category = "Food", date = System.currentTimeMillis() - 23 * 86400000L, amount = -27.45, message = "Con amigos del trabajo"),
                EntityTransaction(title = "YouTube Premium", category = "Entertainment", date = System.currentTimeMillis() - 38 * 86400000L, amount = -10.50, message = "Suscripción mensual"),
                EntityTransaction(title = "Combustible", category = "Transport", date = System.currentTimeMillis() - 43 * 86400000L, amount = -50.00, message = "Llenado de tanque"),
                EntityTransaction(title = "Helado en Freddo", category = "Food", date = System.currentTimeMillis() - 9 * 86400000L, amount = -6.70, message = "Dulce de leche + frutilla"),
                EntityTransaction(title = "Pago del gas", category = "Rent", date = System.currentTimeMillis() - 14 * 86400000L, amount = -9.80, message = "Factura mensual")
            )

                    //TODO POPULATE CON LAS TRANSACCIONES CORRECTAS
            // Inserta todas las transacciones en la base de datos
            transactions.forEach {
                transactionDao.insertTransaction(it)
            }
        }
    }
}

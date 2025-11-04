package com.example.parcial_pr3_ort

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.parcial_pr3_ort.api.RetrofitClient
import com.example.parcial_pr3_ort.ui.screens.RootNavigationGraph
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme
import kotlinx.coroutines.launch
import kotlin.collections.isNotEmpty

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        testApi()
        setContent {
            PARCIALPR3ORTTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RootNavigationGraph() // << ESTE ES EL PUNTO DE ENTRADA AHORA
                }
            }
        }
    }

    private fun testApi() {
        lifecycleScope.launch {


            Log.i(TAG, "------------------------------------------------------")


            Log.i(TAG, "------------------------------------------------------")

            try {
                val responseUsers = RetrofitClient.api.getUserById(1)

                if(responseUsers.isSuccessful){
                    val userDetail = responseUsers.body()
                    if(userDetail != null) {
                        Log.i(TAG, "Llamada a la API de usuario exitosa:")
                        Log.i(TAG, "ID de Usuario: ${userDetail.id}")
                        Log.i(TAG, "address: ${userDetail.address}")
                        Log.i(TAG, "Nombre: ${userDetail.name}")
                        Log.i(TAG, "Nombre de usuario: ${userDetail.username}")
                        Log.i(TAG, "contraseña: ${userDetail.password}")
                        Log.i(TAG, "Email: ${userDetail.email}")
                        Log.i(TAG, "telefono: ${userDetail.phone}")

                    } else {
                        Log.e(TAG, "La respuesta de usuario está vacía (body is null)")
                    }
                } else {
                    Log.e(TAG, "Error en la llamada de usuario: ${responseUsers.code()} - ${responseUsers.message()}")
                }


                Log.i(TAG, "----------------------------------------------------------------------------------------------------")
                val response = RetrofitClient.api.getUserAccount()


                if (response.isSuccessful) {

                    val userAccount = response.body()

                    if (userAccount != null) {
                        Log.i(TAG, "Llamada a la API exitosa:")
                        Log.i(TAG, "ID de Usuario: ${userAccount.userId}")
                        Log.i(TAG, "Balance: ${userAccount.balance}")
                        Log.i(TAG, "Ingresos: ${userAccount.income}")
                        Log.i(TAG, "Gastos: ${userAccount.expense}")
                        Log.i(TAG, "Número de transacciones: ${userAccount.transactions.size}")

                        if (userAccount.transactions.isNotEmpty()) {
                            Log.i(TAG, "Primera transacción: ${userAccount.transactions[1]}")
                        }

                    } else {
                        Log.e(TAG, "La respuesta está vacía (body is null)")
                    }
                } else {
                    Log.e(TAG, "Error en la llamada: ${response.code()} - ${response.message()}")
                }

            } catch (e: Exception) {
                Log.e(TAG, "Excepción al llamar a la API", e)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PARCIALPR3ORTTheme {
    }
}

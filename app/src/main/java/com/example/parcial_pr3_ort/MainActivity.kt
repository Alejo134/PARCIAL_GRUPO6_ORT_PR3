package com.example.parcial_pr3_ort

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.parcial_pr3_ort.ui.screens.CreateAccountScreen
import com.example.parcial_pr3_ort.ui.screens.LoginScreen
import com.example.parcial_pr3_ort.ui.theme.PARCIALPR3ORTTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PARCIALPR3ORTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    LoginScreen()
    //CreateAccountScreen()
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PARCIALPR3ORTTheme {
        Greeting("Android")
    }
}
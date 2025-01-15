package com.verycool.cocktailapi.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.verycool.cocktailapi.view.login.LoginScreen
import com.verycool.cocktailapi.view.theme.CocktailAPITheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CocktailAPITheme { // Your app theme
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LoginScreen() // Your LoginScreen Composable
                }
            }
        }
    }
}
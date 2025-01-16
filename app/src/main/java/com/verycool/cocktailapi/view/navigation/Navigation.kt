package com.verycool.cocktailapi.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.verycool.cocktailapi.view.login.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val loginAuth by remember { mutableStateOf(Firebase.auth) }

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) { LoginScreen(navController = navController, loginAuth) }
    }
}
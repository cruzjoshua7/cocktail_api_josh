package com.verycool.cocktailapi.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.verycool.cocktailapi.presenter.CockTailViewModel
import com.verycool.cocktailapi.view.drinkdetails.CockTailScreen
import com.verycool.cocktailapi.view.drinkslist.CockTailListScreen
import com.verycool.cocktailapi.view.login.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val loginAuth by remember { mutableStateOf(Firebase.auth) }
    val viewModel = remember { CockTailViewModel() }

    LaunchedEffect(Unit) {
        viewModel.fetchDrinksByLetter("A")
    }

    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) { LoginScreen(navController = navController, loginAuth) }
        composable(
            route = Screen.CockTailScreen.route + "/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) { entry ->
            CockTailScreen(navController = navController , name = entry.arguments?.getString("name"),viewModel) }
        composable(route = Screen.CockTailListScreen.route) { CockTailListScreen(navController = navController,viewModel) }

    }
}
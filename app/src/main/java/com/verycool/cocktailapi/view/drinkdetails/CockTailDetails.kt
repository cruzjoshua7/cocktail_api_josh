package com.verycool.cocktailapi.view.drinkdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.verycool.cocktailapi.presenter.CockTailViewModel

@Composable
fun CockTailScreen(
    navController: NavController,
    name: String?,
    viewModel: CockTailViewModel
) {
    Text("$name")


}
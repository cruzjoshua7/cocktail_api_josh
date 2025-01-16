package com.verycool.cocktailapi.view.drinkdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.verycool.cocktailapi.presenter.CockTailViewModel
@Composable
fun CockTailScreen(
    navController: NavController,
    name: String?,
    viewModel: CockTailViewModel
) {
    // Trigger fetching the drink details when the screen is displayed
    LaunchedEffect(name) {
        name?.let {
            viewModel.fetchDrinkByName(it)
        }
    }

    val drinks by viewModel.drinks.collectAsState()
    val error by viewModel.error.collectAsState()

    // Main UI content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display error message if any
        if (error != null) {
            Text(
                text = error ?: "Unknown error",
                color = Color.Red,
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center
            )
        }

        // Display drink details if fetched
        if (drinks.isNotEmpty()) {
            val drink = drinks.first() // Assuming one drink matches the name
            Text(
                text = drink.strDrink ?: "Drink Name",
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Thumbnail Image
            if (!drink.strDrinkThumb.isNullOrEmpty()) {
                Image(
                    painter = rememberImagePainter(data = drink.strDrinkThumb),
                    contentDescription = drink.strDrink,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .padding(8.dp)
                )
            }

            // Drink Details
            Text(
                text = "Category: ${drink.strCategory ?: "N/A"}",
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "Alcoholic: ${drink.strAlcoholic ?: "N/A"}",
                modifier = Modifier.padding(top = 4.dp)
            )
            Text(
                text = "Glass: ${drink.strGlass ?: "N/A"}",
                modifier = Modifier.padding(top = 4.dp)
            )

            // Ingredients
            Text(
                text = "Ingredients:",
                modifier = Modifier.padding(top = 16.dp)
            )
//            Column(modifier = Modifier.padding(start = 16.dp)) {
//                drink.ingredients.forEach { ingredient ->
//                    Text(text = ingredient ?: "N/A")
//                }
//            }

            // Instructions
            Text(
                text = "Instructions:",
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = drink.strInstructions ?: "No instructions provided.",
                modifier = Modifier.padding(top = 8.dp)
            )
        } else {
            // Display a loading or empty state while fetching data
            Text(
                text = "Searching for drink details...",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
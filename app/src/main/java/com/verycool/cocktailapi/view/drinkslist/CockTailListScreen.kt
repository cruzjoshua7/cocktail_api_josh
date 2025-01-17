package com.verycool.cocktailapi.view.drinkslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.verycool.cocktailapi.R
import com.verycool.cocktailapi.domain.model.DrinkModel
import com.verycool.cocktailapi.presenter.CockTailViewModel
import com.verycool.cocktailapi.view.navigation.Screen

@Composable
fun CockTailListScreen(navController: NavController, viewModel: CockTailViewModel) {
    val drinks by viewModel.drinks.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        // Title
        Text(
            text = "Cocktail List",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )

        // Top Image
        Image(
            painter = painterResource(id = R.drawable.group_drinnk), // Replace with your drawable resource
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
        ) {
            TextField(
                value = "",
                onValueChange = { /* Add your search functionality */ },
                placeholder = { Text(text = "Search for a cocktail...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp, end = 4.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                singleLine = true,

            )
            IconButton(
                onClick = { /* Add your search click functionality */ },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        }

        // Alphabet Row
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(('A'..'Z').toList()) { letter ->
                Text(
                    text = letter.toString(),
                    modifier = Modifier
                        .clickable {
                            viewModel.fetchDrinksByLetter(letter.toString())
                        }
                        .padding(8.dp),
                )
            }
        }

        // Error Message
        if (error != null) {
            Text(
                text = error!!,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Drinks Lazy Column
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(drinks) { drink ->
                DrinkCard(drink = drink, navController)
            }
        }
    }
}

@Composable
fun DrinkCard(drink: DrinkModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.CockTailScreen.withArgs(drink.strDrink.toString()))
            }
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            // Drink Thumbnail
            val imageUrl = drink.strDrinkThumb ?: ""
            if (imageUrl.isNotEmpty()) {
                Image(
                    painter = rememberImagePainter(data = imageUrl, builder = {
                        placeholder(R.drawable.ic_launcher_background) // Replace with your placeholder resource
                        error(R.drawable.ic_launcher_background)           // Replace with your error resource
                    }),
                    contentDescription = drink.strDrink,
                    modifier = Modifier.size(64.dp)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(Color.Gray, shape = CircleShape),
                ) {
                    Text(
                        text = "No Image",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Drink Name
            Text(
                text = drink.strDrink ?: "Unknown",
            )
        }
    }
}
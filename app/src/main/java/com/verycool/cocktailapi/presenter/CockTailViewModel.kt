package com.verycool.cocktailapi.presenter

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.verycool.cocktailapi.R
import com.verycool.cocktailapi.data.Drinks

class CockTailViewModel: ViewModel() {
    private val _drinks = mutableStateListOf(
        Drinks("Mojito", "Refreshing and refreshing", R.drawable.ic_launcher_background, 4.5),
        Drinks("Mojito", "Refreshing and refreshing", R.drawable.ic_launcher_background, 4.5),
        Drinks("Mojito", "Refreshing and refreshing", R.drawable.ic_launcher_background, 4.5)
    )

    val drinks: List<Drinks> get() = _drinks
}
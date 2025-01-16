package com.verycool.cocktailapi.presenter

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.verycool.cocktailapi.R
import com.verycool.cocktailapi.data.api.RetroFitClient
import com.verycool.cocktailapi.domain.model.DrinkDetailsModel
import com.verycool.cocktailapi.domain.model.DrinkModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class CockTailViewModel : ViewModel() {
    // StateFlow for the list of drinks
    private val _drinks = MutableStateFlow<List<DrinkModel>>(emptyList())
    val drinks: StateFlow<List<DrinkModel>> = _drinks


    // StateFlow for error messages
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Function to fetch drinks by the letter
    fun fetchDrinksByLetter(letter: String) {
        viewModelScope.launch {
            try {
                val response = RetroFitClient.apiInstance.getListByLetter(letter)
                _drinks.value = response.drinks?.filterNotNull() ?: emptyList()
            } catch (e: Exception) {
                _error.value = e.message ?: "An unexpected error occurred"
            }
        }
    }

    fun fetchDrinkByName(name: String) {
        viewModelScope.launch {
            try {
                val response = RetroFitClient.apiInstance.getDrinkByName(name)
                _drinks.value = response.drinks?.filterNotNull() ?: emptyList()
            } catch (e: Exception) {
                _error.value = e.message ?: "An unexpected error occurred"
            }
        }
    }

}
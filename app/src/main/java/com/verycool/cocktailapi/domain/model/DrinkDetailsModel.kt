package com.verycool.cocktailapi.domain.model


import com.google.gson.annotations.SerializedName

data class DrinkDetailsModel(
    @SerializedName("drinks")
    val drinks: List<DrinkModel?>? = listOf()
)
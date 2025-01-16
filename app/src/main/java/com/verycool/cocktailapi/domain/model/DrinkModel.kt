package com.verycool.cocktailapi.domain.model


import com.google.gson.annotations.SerializedName

data class DrinkModel(

    @SerializedName("idDrink")
    val idDrink: String? = "",
    @SerializedName("strAlcoholic")
    val strAlcoholic: String? = "",
    @SerializedName("strCategory")
    val strCategory: String? = "",
    @SerializedName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String? = "",
    @SerializedName("strDrink")
    val strDrink: String? = "",

    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String? = "",
    @SerializedName("strGlass")
    val strGlass: String? = "",
    @SerializedName("strIBA")
    val strIBA: String? = "",
    @SerializedName("strImageAttribution")
    val strImageAttribution: String? = "",
    @SerializedName("strImageSource")
    val strImageSource: String? = "",
    @SerializedName("strIngredient1")
    val strIngredient1: String? = "",

    @SerializedName("strIngredient2")
    val strIngredient2: String? = "",
    @SerializedName("strIngredient3")
    val strIngredient3: String? = "",
    @SerializedName("strIngredient4")
    val strIngredient4: String? = "",
    @SerializedName("strIngredient5")
    val strIngredient5: String? = "",
    @SerializedName("strIngredient6")
    val strIngredient6: String? = "",

    @SerializedName("strInstructions")
    val strInstructions: String? = "",
    @SerializedName("strInstructionsDE")
    val strInstructionsDE: String? = "",
    @SerializedName("strInstructionsES")
    val strInstructionsES: String? = "",
    @SerializedName("strInstructionsFR")
    val strInstructionsFR: String? = "",
    @SerializedName("strInstructionsIT")
    val strInstructionsIT: String? = "",

    @SerializedName("strMeasure1")
    val strMeasure1: String? = "",
    @SerializedName("strMeasure2")
    val strMeasure2: String? = "",
    @SerializedName("strMeasure3")
    val strMeasure3: String? = "",
    @SerializedName("strMeasure4")
    val strMeasure4: String? = "",
    @SerializedName("strMeasure5")
    val strMeasure5: String? = "",
    @SerializedName("strMeasure6")
    val strMeasure6: String? = "",

    @SerializedName("strTags")
    val strTags: String? = "",
    @SerializedName("strVideo")
    val strVideo: String? = ""
)
package com.verycool.cocktailapi.data.api

import com.verycool.cocktailapi.domain.model.DrinkDetailsModel
import com.verycool.cocktailapi.domain.model.DrinkModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET(ApiDetails.END_POINT_SEARCH)
    suspend fun getListByLetter(
        @Query("f") letter: String
    ): DrinkDetailsModel

    @GET(ApiDetails.END_POINT_SEARCH)
    suspend fun getDrinkByName(
        @Query("s") name:String
    ): DrinkModel

}
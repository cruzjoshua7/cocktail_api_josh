package com.verycool.cocktailapi.data.api

import com.verycool.cocktailapi.domain.model.DrinkDetailsModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET(ApiDetails.END_POINT_SEARCH)
    suspend fun getListByLetter(
        @Query("f") letter: String
    ): DrinkDetailsModel
}
package com.verycool.cocktailapi.data

import com.verycool.cocktailapi.data.api.ApiClient
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClientTest {


    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiClient: ApiClient

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiClient = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiClient::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetListByLetterFetchCorrectData() = runBlocking {
        // Mock JSON response
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                {
                    "drinks": [
                        {
                            "idDrink": "11007",
                            "strDrink": "Margarita",
                            "strCategory": "Cocktail"
                        }
                    ]
                }
                """.trimIndent()
            )
        mockWebServer.enqueue(mockResponse)

        // Execute the API call
        val response = apiClient.getListByLetter("a")

        // Assertions
        assertEquals(1, response.drinks?.size)
        assertEquals("11007", response.drinks?.first()?.idDrink)
        assertEquals("Margarita", response.drinks?.first()?.strDrink)
    }


}
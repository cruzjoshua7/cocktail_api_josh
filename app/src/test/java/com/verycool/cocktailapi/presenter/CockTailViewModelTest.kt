package com.verycool.cocktailapi.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.verycool.cocktailapi.data.api.ApiClient
import com.verycool.cocktailapi.data.api.RetroFitClient
import com.verycool.cocktailapi.domain.model.DrinkDetailsModel
import com.verycool.cocktailapi.domain.model.DrinkModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class CockTailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() // To ensure LiveData works on the main thread in tests.

    private val mockApiClient = mock<ApiClient>() // Mocked API client
    private lateinit var viewModel: CockTailViewModel

    private val testDispatcher = StandardTestDispatcher() // Test dispatcher for coroutines

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        RetroFitClient.apiInstance = mockApiClient // Replace actual instance with mocked client
        viewModel = CockTailViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetchDrinkByLetterSuccess() = runTest {
        // Mock API response
        val mockResponse = DrinkDetailsModel(
            drinks = listOf(
                DrinkModel(idDrink = "11007", strDrink = "Margarita"),
                DrinkModel(idDrink = "11008", strDrink = "Mojito")
            )
        )
        whenever(mockApiClient.getListByLetter("m")).thenReturn(mockResponse)

        // Call the function
        viewModel.fetchDrinksByLetter("m")
        testDispatcher.scheduler.advanceUntilIdle() // Advance coroutine execution

        // Assert StateFlow values
        val drinks = viewModel.drinks.first()
        Assert.assertEquals(2, drinks.size)
        Assert.assertEquals("Margarita", drinks[0].strDrink)
    }

    @Test
    fun fetchDrinkByLetterError() = runTest {
        // Mock API to throw an exception
        val mockException = RuntimeException("Network error")
        whenever(mockApiClient.getListByLetter("z")).thenThrow(mockException)

        // Call the function
        viewModel.fetchDrinksByLetter("z")
        testDispatcher.scheduler.advanceUntilIdle() // Advance coroutine execution

        // Assert StateFlow error value
        val error = viewModel.error.first()
        Assert.assertEquals("Network error", error)
    }
}
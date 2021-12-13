package io.minoro75.demoweatherapp.data.remote

import io.minoro75.demoweatherapp.domain.cities_suggestions.model.SuggestionsResponse
import io.minoro75.demoweatherapp.domain.forecast.model.ForecastResponse
import io.minoro75.demoweatherapp.domain.geocoding.model.CityNameResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getForecastFromCoordinates(lat: Double, lon: Double): Flow<ForecastResponse>
    suspend fun getCitiesSuggestions(enteredRequest: String): Flow<SuggestionsResponse>
    suspend fun getCityNameFromCoordinates(lat: Double, lon: Double): Flow<CityNameResponse>
}

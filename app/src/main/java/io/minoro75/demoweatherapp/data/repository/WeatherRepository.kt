package io.minoro75.demoweatherapp.data.repository

import io.minoro75.demoweatherapp.data.remote.RemoteDataSourceImpl
import io.minoro75.demoweatherapp.domain.cities_suggestions.model.SuggestionsResponse
import io.minoro75.demoweatherapp.domain.forecast.model.ForecastResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    suspend fun getForecastFromCoordinates(lat: Double, lon: Double): Flow<ForecastResponse> =
        remoteDataSourceImpl.getForecastFromCoordinates(lat, lon)
            .flowOn(Dispatchers.IO)

    suspend fun getCitiesSuggestions(enteredRequest: String): Flow<SuggestionsResponse> =
        remoteDataSourceImpl.getCitiesSuggestions(enteredRequest)
            .flowOn(Dispatchers.IO)
}

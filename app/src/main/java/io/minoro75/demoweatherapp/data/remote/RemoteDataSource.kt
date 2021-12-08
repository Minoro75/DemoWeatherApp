package io.minoro75.demoweatherapp.data.remote

import io.minoro75.demoweatherapp.domain.forecast.model.ForecastResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getForecastFromCoordinates(lat: Double, lon: Double): Flow<ForecastResponse>
}

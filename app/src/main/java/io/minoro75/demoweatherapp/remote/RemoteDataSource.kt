package io.minoro75.demoweatherapp.remote

import io.minoro75.demoweatherapp.forecast_entity.ForecastLocation
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getWeatherInCity(city: String): Flow<ForecastLocation>
    suspend fun getCityNameFromCoordinates(lat: Double, lon: Double): Flow<String>
}

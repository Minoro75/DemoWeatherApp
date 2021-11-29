package io.minoro75.heremapsweatherapp.remote

import io.minoro75.heremapsweatherapp.domain.ForecastLocation
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getWeatherInCity(city: String): Flow<ForecastLocation>
}

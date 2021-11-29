package io.minoro75.heremapsweatherapp.network

import io.minoro75.heremapsweatherapp.domain.Weather
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getWeatherInCity(city: String): Flow<List<Weather>>
}

package io.minoro75.demoweatherapp.data.repository

import io.minoro75.demoweatherapp.data.remote.RemoteDataSourceImpl
import io.minoro75.demoweatherapp.domain.forecasts.model.ForecastLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    suspend fun getWeatherInCity(city: String): Flow<ForecastLocation> =
        remoteDataSourceImpl.getWeatherInCity(city)
            .flowOn(Dispatchers.IO)

    suspend fun getCityNameFromCoordinates(lat: Double, lon: Double): Flow<String> =
        remoteDataSourceImpl.getCityNameFromCoordinates(lat, lon)
            .flowOn(Dispatchers.IO)
}

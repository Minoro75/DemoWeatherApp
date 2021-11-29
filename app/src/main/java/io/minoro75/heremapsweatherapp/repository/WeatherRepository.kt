package io.minoro75.heremapsweatherapp.repository

import io.minoro75.heremapsweatherapp.domain.forecast_entity.ForecastLocation
import io.minoro75.heremapsweatherapp.remote.RemoteDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    suspend fun getWeatherInCity(city: String): Flow<ForecastLocation> =
        remoteDataSourceImpl.getWeatherInCity(city)
            .flowOn(Dispatchers.Default)

    suspend fun getCityNameFromCoordinates(lat: Double, lon: Double): Flow<String> =
        remoteDataSourceImpl.getCityNameFromCoordinates(lat, lon)
            .flowOn(Dispatchers.Default)
}
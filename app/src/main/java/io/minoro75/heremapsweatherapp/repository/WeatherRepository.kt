package io.minoro75.heremapsweatherapp.repository

import io.minoro75.heremapsweatherapp.domain.ForecastLocation
import io.minoro75.heremapsweatherapp.remote.RemoteDataSourceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSourceImpl: RemoteDataSourceImpl
) {
    suspend fun getWeatherInCity(city: String): Flow<ForecastLocation> {
        return remoteDataSourceImpl.getWeatherInCity(city)
            .flowOn(Dispatchers.Default)
    }
}

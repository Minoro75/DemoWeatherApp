package io.minoro75.heremapsweatherapp.remote

import io.minoro75.heremapsweatherapp.domain.ForecastLocation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteDataSource {
    override suspend fun getWeatherInCity(city: String): Flow<ForecastLocation> {
        return flow {
            emit(remoteService.getWeatherInCity(city).forecasts?.forecastLocation!!)
        }
    }
}

package io.minoro75.heremapsweatherapp.remote

import io.minoro75.heremapsweatherapp.domain.forecast_entity.ForecastLocation
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

    override suspend fun getCityNameFromCoordinates(lat: Double, lon: Double): Flow<String> {
        return flow {
            emit(remoteService.getCityNameFromCoordinates(lat, lon).observations.location.first().city)
        }
    }
}

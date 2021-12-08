package io.minoro75.demoweatherapp.data.remote

import io.minoro75.demoweatherapp.domain.forecast.model.ForecastResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val remoteService: RemoteService
) : RemoteDataSource {

    override suspend fun getForecastFromCoordinates(lat: Double, lon: Double): Flow<ForecastResponse> {
        return flow {
            emit(remoteService.getForecastFromCoordinates(lat, lon))
        }
    }
}

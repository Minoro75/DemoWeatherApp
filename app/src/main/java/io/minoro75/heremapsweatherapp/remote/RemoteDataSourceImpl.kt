package io.minoro75.heremapsweatherapp.remote

import io.minoro75.heremapsweatherapp.domain.Weather
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor() : RemoteDataSource {
    override suspend fun getWeatherInCity(city: String): Flow<List<Weather>> {
        TODO("Not yet implemented")
    }
}

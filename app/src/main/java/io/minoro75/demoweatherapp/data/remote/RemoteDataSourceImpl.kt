package io.minoro75.demoweatherapp.data.remote

import io.minoro75.demoweatherapp.domain.cities_suggestions.model.SuggestionsResponse
import io.minoro75.demoweatherapp.domain.forecast.model.ForecastResponse
import io.minoro75.demoweatherapp.domain.geocoding.model.CityNameResponse
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

    override suspend fun getCitiesSuggestions(enteredRequest: String): Flow<SuggestionsResponse> {
        return flow {
            emit(remoteService.getCitySuggestions(enteredRequest))
        }
    }

    override suspend fun getCityNameFromCoordinates(lat: Double, lon: Double): Flow<CityNameResponse> {
        return flow {
            emit(remoteService.getCityNameFromCoordinates(lat, lon))
        }
    }
}

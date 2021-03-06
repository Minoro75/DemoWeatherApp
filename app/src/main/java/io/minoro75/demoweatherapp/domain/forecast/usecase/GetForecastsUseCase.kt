package io.minoro75.demoweatherapp.domain.forecast.usecase

import io.minoro75.demoweatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetForecastsUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double) = repository.getForecastFromCoordinates(lat, lon)
}

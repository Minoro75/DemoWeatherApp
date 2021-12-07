package io.minoro75.demoweatherapp.domain.forecasts.usecase

import io.minoro75.demoweatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(cityName: String) = repository.getWeatherInCity(cityName)
}

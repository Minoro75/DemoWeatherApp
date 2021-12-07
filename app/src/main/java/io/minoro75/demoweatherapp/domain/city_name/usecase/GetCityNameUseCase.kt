package io.minoro75.demoweatherapp.domain.city_name.usecase

import io.minoro75.demoweatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetCityNameUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(lat: Double, lon: Double) = repository.getCityNameFromCoordinates(lat, lon)
}

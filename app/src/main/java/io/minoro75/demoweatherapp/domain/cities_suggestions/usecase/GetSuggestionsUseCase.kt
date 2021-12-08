package io.minoro75.demoweatherapp.domain.cities_suggestions.usecase

import io.minoro75.demoweatherapp.data.repository.WeatherRepository
import javax.inject.Inject

class GetSuggestionsUseCase @Inject constructor(private val repository: WeatherRepository) {
    suspend operator fun invoke(enteredRequest: String) = repository.getCitiesSuggestions(enteredRequest)
}

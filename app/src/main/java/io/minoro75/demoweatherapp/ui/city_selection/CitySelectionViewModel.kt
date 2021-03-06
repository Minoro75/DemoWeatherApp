package io.minoro75.demoweatherapp.ui.city_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minoro75.demoweatherapp.domain.cities_suggestions.model.Suggestions
import io.minoro75.demoweatherapp.domain.cities_suggestions.usecase.GetSuggestionsUseCase
import io.minoro75.demoweatherapp.domain.geocoding.usecase.GetCityNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CitySelectionViewModel @Inject constructor(
    private val getSuggestionsUseCase: GetSuggestionsUseCase,
    private val getCityNameUseCase: GetCityNameUseCase
) : ViewModel() {

    private val _latitude = MutableStateFlow(0.0)
    val latitude: StateFlow<Double> = _latitude

    private val _longitude = MutableStateFlow(0.0)
    val longitude: StateFlow<Double> = _longitude

    private val _suggestions = MutableStateFlow<List<Suggestions>?>(null)
    val suggestions: StateFlow<List<Suggestions>?> = _suggestions

    fun getCitiesSuggestions(enteredRequest: String) {
        viewModelScope.launch {
            getSuggestionsUseCase.invoke(enteredRequest).collect {
                _suggestions.emit(it.records)
            }
        }
    }

    fun getCityNameFromCoordinates(lat: Double, lon: Double): String {
        // TODO: 12/13/2021 Tech Debt: find a better solution for this issue
        var city = ""
        runBlocking {
            getCityNameUseCase.invoke(lat, lon).collect {
                city = it.cityName
            }
        }
        return city
    }

    fun setCoordinates(lat: Double, lon: Double) {
        _latitude.value = lat
        _longitude.value = lon
    }
}

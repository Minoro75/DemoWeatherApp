package io.minoro75.demoweatherapp.ui.city_selection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minoro75.demoweatherapp.domain.city_name.usecase.GetCityNameUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CitySelectionViewModel @Inject constructor(
    private val getCityNameUseCase: GetCityNameUseCase
) : ViewModel() {

    private val _city = MutableStateFlow("")
    val city: StateFlow<String> = _city

    private val _latitude = MutableStateFlow(0.0)
    val latitude: StateFlow<Double> = _latitude

    private val _longitude = MutableStateFlow(0.0)
    val longitude: StateFlow<Double> = _longitude

    /* fun getCityNameFromCoordinates(lat: Double, lon: Double) {
         viewModelScope.launch {
             getCityNameUseCase(lat, lon).collect {
                 _city.emit(it)
             }
         }
     }*/
    fun setCoordinates(lat: Double, lon: Double) {
        _latitude.value = lat
        _longitude.value = lon
    }
}

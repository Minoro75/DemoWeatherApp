package io.minoro75.heremapsweatherapp.ui.city_selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minoro75.heremapsweatherapp.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class CitySelectionViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _lat = MutableLiveData<Double>()
    val lat: LiveData<Double> = _lat

    private val _lon = MutableLiveData<Double>()
    val lon: LiveData<Double> = _lon

    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    fun getCityNameFromCoordinates(lat: Double, lon: Double) {
    }
}

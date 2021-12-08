package io.minoro75.demoweatherapp.ui.city_forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minoro75.demoweatherapp.domain.common.Resource
import io.minoro75.demoweatherapp.domain.forecast.model.Weather
import io.minoro75.demoweatherapp.domain.forecast.usecase.GetForecastsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityForecastViewModel @Inject constructor(
    private val getForecastsUseCase: GetForecastsUseCase
) : ViewModel() {
    private val _weather = MutableStateFlow<Resource<List<Weather>>>(Resource.loading(null))
    val weather: StateFlow<Resource<List<Weather>>> = _weather

    private val _city = MutableStateFlow<String>("default")
    val city: StateFlow<String> = _city

    fun getWeatherInCity(city: String) {
        viewModelScope.launch {
            _weather.emit(Resource.loading(null))
            try {
                getForecastsUseCase(city).collect {
                    _weather.emit(Resource.success(it.forecast))
                    _city.emit(it.city)
                }
            } catch (ex: Exception) {
                _weather.emit(Resource.error(null, ex.localizedMessage))
            }
        }
    }
}

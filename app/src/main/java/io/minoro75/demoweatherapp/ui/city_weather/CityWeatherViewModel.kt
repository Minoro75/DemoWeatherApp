package io.minoro75.demoweatherapp.ui.city_weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minoro75.demoweatherapp.data.repository.WeatherRepository
import io.minoro75.demoweatherapp.domain.forecasts.model.Weather
import io.minoro75.demoweatherapp.utils.Resource
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityWeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    private val _weather = MutableLiveData<Resource<List<Weather>>>()
    val weather: LiveData<Resource<List<Weather>>> = _weather

    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    fun getWeatherInCity(city: String) {
        viewModelScope.launch {
            _weather.value = Resource.loading(null)
            try {
                weatherRepository.getWeatherInCity(city).collect {
                    _weather.value = Resource.success(it.forecast)
                    _city.value = it.city
                }
            } catch (ex: Exception) {
                _weather.value = Resource.error(null, ex.localizedMessage)
            }
        }
    }
}

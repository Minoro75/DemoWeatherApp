package io.minoro75.demoweatherapp.ui.city_forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minoro75.demoweatherapp.domain.common.Resource
import io.minoro75.demoweatherapp.domain.forecasts.model.Weather
import io.minoro75.demoweatherapp.domain.forecasts.usecase.GetForecastsUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityForecastViewModel @Inject constructor(
    private val getForecastsUseCase: GetForecastsUseCase
) : ViewModel() {
    private val _weather = MutableLiveData<Resource<List<Weather>>>()
    val weather: LiveData<Resource<List<Weather>>> = _weather

    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    fun getWeatherInCity(city: String) {
        viewModelScope.launch {
            _weather.value = Resource.loading(null)
            try {
                getForecastsUseCase(city).collect {
                    _weather.value = Resource.success(it.forecast)
                    _city.value = it.city
                }
            } catch (ex: Exception) {
                _weather.value = Resource.error(null, ex.localizedMessage)
            }
        }
    }
}

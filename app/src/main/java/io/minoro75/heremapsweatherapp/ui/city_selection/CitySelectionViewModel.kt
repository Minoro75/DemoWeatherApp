package io.minoro75.heremapsweatherapp.ui.city_selection

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minoro75.heremapsweatherapp.repository.WeatherRepository
import javax.inject.Inject

@HiltViewModel
class CitySelectionViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    // TODO: Implement the ViewModel
}

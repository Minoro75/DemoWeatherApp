package io.minoro75.demoweatherapp.ui.city_selection

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.minoro75.demoweatherapp.domain.city_name.usecase.GetCityNameUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitySelectionViewModel @Inject constructor(
    private val getCityNameUseCase: GetCityNameUseCase
) : ViewModel() {

    private val _city = MutableLiveData<String>()
    val city: LiveData<String> = _city

    fun getCityNameFromCoordinates(lat: Double, lon: Double) {
        viewModelScope.launch {
            getCityNameUseCase(lat, lon).collect {
                _city.value = it
            }
        }
    }
}

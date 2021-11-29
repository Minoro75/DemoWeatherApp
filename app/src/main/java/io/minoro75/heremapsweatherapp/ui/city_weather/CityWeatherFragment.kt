package io.minoro75.heremapsweatherapp.ui.city_weather

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.databinding.FragmentCityWeatherBinding

@AndroidEntryPoint
class CityWeatherFragment : Fragment(R.layout.fragment_city_weather) {

    private val binding: FragmentCityWeatherBinding by viewBinding()

    private val viewModel: CityWeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

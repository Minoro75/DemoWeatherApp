package io.minoro75.heremapsweatherapp.cityWeatherFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.databinding.FragmentCityWeatherBinding

class CityWeatherFragment : Fragment(R.layout.fragment_city_weather) {

    private val binding: FragmentCityWeatherBinding by viewBinding()

    private lateinit var viewModel: CityWeatherViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CityWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

package io.minoro75.heremapsweatherapp.ui.city_weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.minoro75.heremapsweatherapp.R

class CityWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = CityWeatherFragment()
    }

    private lateinit var viewModel: CityWeatherViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view?.clipToOutline = true
        return inflater.inflate(R.layout.fragment_city_weather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CityWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

package io.minoro75.heremapsweatherapp.ui.city_weather

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.databinding.FragmentCityWeatherBinding
import io.minoro75.heremapsweatherapp.utils.Status
import io.minoro75.heremapsweatherapp.utils.toInvisible
import io.minoro75.heremapsweatherapp.utils.toVisible
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@AndroidEntryPoint
class CityWeatherFragment : Fragment(R.layout.fragment_city_weather) {

    private val binding: FragmentCityWeatherBinding by viewBinding()
    private val args: CityWeatherFragmentArgs by navArgs()
    private val viewModel: CityWeatherViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weatherAdapter = WeatherAdapter(arrayListOf())
        with(binding) {
            rvWeatherList.layoutManager = LinearLayoutManager(context)
            rvWeatherList.adapter = weatherAdapter
            srlSwipeContainer.setOnRefreshListener {
                weatherAdapter.clear()
                viewModel.getWeatherInCity(args.city)
            }
            srlSwipeContainer.setColorSchemeResources(R.color.primaryDarkColor)
            btBack.setOnClickListener { findNavController().navigateUp() }
            lifecycleScope.launch {
                viewModel.getWeatherInCity(args.city)
                viewModel.weather.observe(viewLifecycleOwner, { resourceList ->
                    when (resourceList.status) {
                        is Status.SUCCESS -> {
                            piCityWeather.toInvisible()
                            rvWeatherList.toVisible()
                            clCurrentWeather.toVisible()
                            val responseUtcFormat =
                                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
                            val date = responseUtcFormat.parse(resourceList.data?.first()?.utcTime)
                            val simpleDateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())
                            srlSwipeContainer.isRefreshing = false
                            weatherAdapter.clear()
                            weatherAdapter.addList(resourceList.data)
                            tvCurrentWeatherTemp.text =
                                getString(R.string.temperature_item, resourceList.data?.first()?.temperature)
                            tvCurrentDate.text = simpleDateFormat.format(date)
                            tvCurrentWeatherDescription.text = resourceList.data?.first()?.description
                            Glide.with(requireContext())
                                .load(resourceList.data?.first()?.iconLink)
                                .into(ivCurrentWeatherIcon)
                        }
                        is Status.LOADING -> {
                            piCityWeather.toVisible()
                            clCurrentWeather.toInvisible()
                            rvWeatherList.toInvisible()
                        }
                        is Status.ERROR -> {
                            Toast.makeText(context, "${resourceList.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
                viewModel.city.observe(viewLifecycleOwner, {
                    tvCurrentLocation.text = it
                })
            }
        }
    }
}

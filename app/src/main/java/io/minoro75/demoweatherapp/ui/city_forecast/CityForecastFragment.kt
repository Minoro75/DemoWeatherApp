package io.minoro75.demoweatherapp.ui.city_forecast

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.minoro75.demoweatherapp.R
import io.minoro75.demoweatherapp.databinding.FragmentCityForecastBinding
import io.minoro75.demoweatherapp.domain.common.Resource
import io.minoro75.demoweatherapp.domain.common.Status
import io.minoro75.demoweatherapp.domain.forecast.model.Forecast
import io.minoro75.demoweatherapp.utils.toInvisible
import io.minoro75.demoweatherapp.utils.toVisible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CityForecastFragment : Fragment(R.layout.fragment_city_forecast) {

    private val binding: FragmentCityForecastBinding by viewBinding()
    private val args: CityForecastFragmentArgs by navArgs()
    private val viewModel: CityForecastViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weatherAdapter = ForecastAdapter(arrayListOf())
        with(binding) {
            rvWeatherList.layoutManager = LinearLayoutManager(context)
            rvWeatherList.adapter = weatherAdapter
            srlSwipeContainer.setOnRefreshListener {
                weatherAdapter.clear()
                viewModel.getWeatherInCity(args.lat.toDouble(), args.lon.toDouble())
            }
            srlSwipeContainer.setColorSchemeResources(R.color.md_theme_light_primary)
            btBack.setOnClickListener { findNavController().navigateUp() }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.getWeatherInCity(args.lat.toDouble(), args.lon.toDouble())
                    viewModel.weather.collect { resourceList ->
                        when (resourceList.status) {
                            is Status.SUCCESS -> {
                                // TODO: 12/7/2021 extract functions
                                piCityWeather.toInvisible()
                                rvWeatherList.toVisible()
                                clCurrentWeather.toVisible()
                                // TODO: 12/7/2021 simplify this block and cleanup  this mess
                                /*val responseUtcFormat =
                                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault())
                                val date = responseUtcFormat.parse(resourceList.data?.first()?.utcTime)
                                val simpleDateFormat = SimpleDateFormat("MM-dd HH:mm", Locale.getDefault())*/
                                srlSwipeContainer.isRefreshing = false
                                weatherAdapter.clear()
                                weatherAdapter.addList(resourceList.data)
                                setCurrentWeather(resourceList)
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
                    }
                }
            }
            lifecycleScope.launch {
                // another collect have to be called in another coroutine
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.city.collect {
                        tvCurrentLocation.text = it
                    }
                }
            }
        }
    }

    private fun FragmentCityForecastBinding.setCurrentWeather(resourceList: Resource<List<Forecast>>) {
        tvCurrentLocation.text = args.city
        tvCurrentWeatherTemp.text =
            getString(
                R.string.temperature_item, resourceList.data?.first()?.temperature?.min?.toInt(),
                resourceList.data?.first()?.temperature?.max?.toInt()
            )
        tvCurrentDate.text = resourceList.data?.first()?.currentTime.toString()
        tvCurrentWeatherDescription.text =
            resourceList.data?.first()?.iconList?.first()?.iconDescription
        Glide.with(requireContext())
            .load("https://openweathermap.org/img/wn/${resourceList.data?.first()?.iconList?.first()?.iconLink}@2x.png")
            .into(ivCurrentWeatherIcon)
    }
}

package io.minoro75.demoweatherapp.ui.city_selection

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import io.minoro75.demoweatherapp.R
import io.minoro75.demoweatherapp.databinding.FragmentCitySelectionBinding
import io.minoro75.demoweatherapp.domain.cities_suggestions.model.Suggestions
import io.minoro75.demoweatherapp.utils.toVisible
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CitySelectionFragment : Fragment(R.layout.fragment_city_selection) {

    private val binding: FragmentCitySelectionBinding by viewBinding()
    private val viewModel: CitySelectionViewModel by viewModels()
    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this.requireContext())
    }
    private var selectedCity: Suggestions? = null
    private var cityName: String? = null
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        val suggestions = mutableListOf<Suggestions>()
        val autoCompleteAdapter =
            CitySelectionAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, suggestions)
        with(binding) {
            setAutoCompleteTextView(autoCompleteAdapter, view)
            ibGetCurrentLocation.setOnClickListener {
                getDeviceLocation()
            }
            btGoToDetails.setOnClickListener {
                goToCityWeatherFragment(false)
            }
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.suggestions.collect {
                        if (it != null) {
                            autoCompleteAdapter.addAllDataToSuggestionsList(it)
                        }
                    }
                }
            }
        }
    }

    private fun goToCityWeatherFragment(isLocationProviderUsed: Boolean) {
        if (selectedCity != null && !isLocationProviderUsed) {
            findNavController().navigate(
                CitySelectionFragmentDirections.actionNavCitySelectionToNavCityWeather(
                    selectedCity?.suggestions?.cityName,
                    // coordinates in response contained in array [lat,lon]
                    selectedCity?.suggestions?.coordinates?.get(0)?.toFloat() as Float,
                    selectedCity?.suggestions?.coordinates?.get(1)?.toFloat() as Float
                )
            )
        } else {
            findNavController().navigate(
                CitySelectionFragmentDirections.actionNavCitySelectionToNavCityWeather(
                    cityName,
                    viewModel.latitude.value.toFloat(),
                    viewModel.longitude.value.toFloat()
                )
            )
        }
    }

    private fun setAutoCompleteTextView(
        autoCompleteAdapter: CitySelectionAdapter,
        view: View
    ) {
        with(binding) {
            actvCityName.setAdapter(autoCompleteAdapter)
            actvCityName.threshold = 2
            actvCityName.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                selectedCity = autoCompleteAdapter.getItem(position)
                actvCityName.setText(selectedCity?.suggestions?.cityName)
                btGoToDetails.toVisible()
            }
            actvCityName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // no-op
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val handler = Handler(Looper.getMainLooper())

                    if (!actvCityName.isPerformingCompletion && s.toString().length > 1) {
                        // if user writes one more symbol - cancel task and prevent requests spam
                        handler.removeMessages(0)
                        handler.postDelayed({ viewModel.getCitiesSuggestions(s.toString()) }, 1000)
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // no-op
                }
            })
            actvCityName.setOnDismissListener {
                hideKeyboard(view)
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val inputManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputManager?.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun requestPermissions() {
        requestMultiplePermissions.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    private fun getDeviceLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                viewModel.setCoordinates(it.latitude, it.longitude)
                cityName = viewModel.getCityNameFromCoordinates(it.latitude, it.longitude)
                goToCityWeatherFragment(true)
            }
        } else {
            requestPermissions()
        }
    }
}

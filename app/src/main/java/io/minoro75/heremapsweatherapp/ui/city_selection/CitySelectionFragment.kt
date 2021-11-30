package io.minoro75.heremapsweatherapp.ui.city_selection

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.databinding.FragmentCitySelectionBinding
import io.minoro75.heremapsweatherapp.utils.toVisible
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CitySelectionFragment : Fragment(R.layout.fragment_city_selection) {

    private val binding: FragmentCitySelectionBinding by viewBinding()
    private val viewModel: CitySelectionViewModel by viewModels()
    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this.requireContext())
    }
    private var selectedCity: String? = null
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        val citiesList = mutableListOf<String>()
        val scope = CoroutineScope(Job() + Dispatchers.IO)
        val autoCompleteAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, citiesList)
        scope.launch {
            citiesList.addAll(resources.getStringArray(R.array.alabama_cities_list))
            citiesList.addAll(resources.getStringArray(R.array.alaska_cities_list))
            citiesList.addAll(resources.getStringArray(R.array.california_cities_list))
            citiesList.addAll(resources.getStringArray(R.array.massachusetts_cities_list))
            citiesList.addAll(resources.getStringArray(R.array.new_york_cities_list))
            citiesList.addAll(resources.getStringArray(R.array.washington_cities_list))
        }

        with(binding) {
            actvCityName.setAdapter(autoCompleteAdapter)
            actvCityName.threshold = 2
            actvCityName.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
                selectedCity = parent.getItemAtPosition(position).toString()
                btGoToDetails.toVisible()
            }
            actvCityName.setOnDismissListener {
                hideKeyboard(view)
            }
            ibGetCurrentLocation.setOnClickListener {
                binding.btGoToDetails.toVisible()
                getDeviceLocation()
            }
            btGoToDetails.setOnClickListener {
                if (!selectedCity.isNullOrEmpty()) {
                    findNavController().navigate(
                        CitySelectionFragmentDirections.actionNavCitySelectionToNavCityWeather(selectedCity)
                    )
                }
            }
            viewModel.city.observe(viewLifecycleOwner, {
                actvCityName.setText(viewModel.city.value)
            })
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
                viewModel.getCityNameFromCoordinates(it.latitude, it.longitude)
            }
        } else {
            requestPermissions()
        }
    }
}

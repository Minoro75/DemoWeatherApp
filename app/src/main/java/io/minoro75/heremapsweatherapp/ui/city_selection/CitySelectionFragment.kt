package io.minoro75.heremapsweatherapp.ui.city_selection

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.databinding.FragmentCitySelectionBinding

@AndroidEntryPoint
class CitySelectionFragment : Fragment(R.layout.fragment_city_selection) {

    private val binding: FragmentCitySelectionBinding by viewBinding()
    private val viewModel: CitySelectionViewModel by viewModels()
    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this.requireContext())
    }
    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermissions()
        binding.imageButton.setOnClickListener {
            getDeviceLocation()
        }
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
                Toast.makeText(context, "${it.latitude} ${it.longitude}", Toast.LENGTH_SHORT).show()
            }
        } else {
            requestPermissions()
        }
    }
}

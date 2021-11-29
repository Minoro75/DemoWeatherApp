package io.minoro75.heremapsweatherapp.ui.city_selection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.databinding.FragmentCitySelectionBinding

@AndroidEntryPoint
class CitySelectionFragment : Fragment(R.layout.fragment_city_selection) {

    private val binding: FragmentCitySelectionBinding by viewBinding()

    private val viewModel: CitySelectionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}

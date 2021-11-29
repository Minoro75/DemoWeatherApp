package io.minoro75.heremapsweatherapp.ui.city_selection

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import io.minoro75.heremapsweatherapp.R
import io.minoro75.heremapsweatherapp.databinding.FragmentCitySelectionBinding

class CitySelectionFragment : Fragment(R.layout.fragment_city_selection) {

    private val binding: FragmentCitySelectionBinding by viewBinding()

    private lateinit var viewModel: CitySelectionViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CitySelectionViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

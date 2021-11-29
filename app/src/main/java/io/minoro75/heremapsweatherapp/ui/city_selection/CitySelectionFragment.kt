package io.minoro75.heremapsweatherapp.ui.city_selection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.minoro75.heremapsweatherapp.R

class CitySelectionFragment : Fragment() {

    companion object {
        fun newInstance() = CitySelectionFragment()
    }

    private lateinit var viewModel: CitySelectionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_city_selection, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CitySelectionViewModel::class.java)
        // TODO: Use the ViewModel
    }
}

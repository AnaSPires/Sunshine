package com.example.sunshine

import WeatherForecastAdaper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.sunshine.data.SunshinePreferences
import com.example.sunshine.databinding.ActivityForecastBinding
import com.example.sunshine.model.WeatherForecast
import com.example.sunshine.viewmodel.WeatherForecastUiState
import com.example.sunshine.viewmodel.WeatherForecastViewModel

class FragmentForecastList : Fragment() {
    private lateinit var binding: ActivityForecastBinding
    private lateinit var data: WeatherForecast
    private val viewModel: WeatherForecastViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = ActivityForecastBinding.inflate(layoutInflater)

        SunshinePreferences().getPreferredWeatherLocation(requireContext())
            ?.let { viewModel.setLocation(it) }

        viewModel.location.observe(viewLifecycleOwner) {
            viewModel.syncWeatherForecast()
        }
        viewModel.tempUnit.observe(viewLifecycleOwner) {
            viewModel.syncWeatherForecast()
        }

        viewModel.status.observe(viewLifecycleOwner)
        { state ->
            if (state is WeatherForecastUiState.Success) {
                data = state.list
                binding.weatherForecastRv.adapter = WeatherForecastAdaper(data) { item ->
                    val action =
                        FragmentForecastListDirections.actionFragmentForecastListToFragmentDetails()
                            .setWeatherForecastInfo(item)
                    Navigation.findNavController(binding.root).navigate(action)
                }
            }

        }

        return binding.root
    }
}


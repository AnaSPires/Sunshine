package com.example.sunshine

import WeatherForecastHourAdaper
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sunshine.databinding.ActivityDetailsBinding
import com.example.sunshine.utils.SunshineDateUtils
import com.example.sunshine.utils.SunshineWeatherUtils


class FragmentDetails : Fragment() {

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        arguments?.let {
            val item  = FragmentDetailsArgs.fromBundle(it).weatherForecastInfo
           item?.let {
               with(binding.activityDetailsPrimaryInfo) {
                   val url = "https:${item.day.condition.icon}"
                   Glide.with(requireContext())
                       .load(url)
                       .into(weatherIcon)
                   date.text =
                       SunshineDateUtils().getFriendlyDateString(
                           requireContext(),
                           item.date, false
                       )
                   weatherDescription.text = item.day.condition.text
                   highTemperature.text =
                       SunshineWeatherUtils().formatTemperature(requireContext(), item.day.maxTempC, item.day.maxTempF)
                   lowTemperature.text =
                       SunshineWeatherUtils().formatTemperature(requireContext(), item.day.minTempC, item.day.minTempF)
               }

               with(binding.activityDetailsExtra){
                   humidity.text = resources.getString(R.string.format_humidity,item.day.avgHumidityPercentage)
                   rain.text = resources.getString(R.string.format_rain, item.day.dailyChanceRainPercentage)
                   windMeasurement.text = resources.getString(R.string.format_wind_mph, item.day.maxWindSpeedKm)
               }

               binding.activityDetailsRv.adapter = WeatherForecastHourAdaper(item.hours)
           }
        }
        return binding.root
    }

}
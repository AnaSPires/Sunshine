package com.example.sunshine.utils

import android.content.Context
import com.example.sunshine.R
import com.example.sunshine.data.SunshinePreferences

class SunshineWeatherUtils {

    fun formatTemperature(context: Context, temperatureC: Double, temperatureF: Double): String {
        return if(SunshinePreferences().getPreferredWeatherTempUnit(context).equals(context.getString(R.string.pref_unit_celsius))){
            String.format(
                context.getString(R.string.format_temperature_celsius),
                temperatureC
            )
        }else{
            String.format(
                context.getString(R.string.format_temperature_fahrenheit),
                temperatureF
            )
        }

    }
}
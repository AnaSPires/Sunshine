package com.example.sunshine.data

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.sunshine.R

class SunshinePreferences {
    private val PREF_LOCATION = "location"
    val PREF_TEMP_UNIT = "temp_unit"


    fun getPreferredWeatherLocation(context: Context?): String? {
        val sp: SharedPreferences? = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }

        val keyForLocation = context?.getString(R.string.pref_location_key)
        val defaultLocation = context?.getString(R.string.pref_location_default)

        return sp?.getString(keyForLocation,defaultLocation )
    }

    fun getPreferredWeatherTempUnit(context: Context?): String? {
        val sp: SharedPreferences? = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }

        val keyForLocation = context?.getString(R.string.pref_unit_temp_key)
        val defaultLocation = context?.getString(R.string.pref_unit_temp_default)

        return sp?.getString(keyForLocation,defaultLocation )
    }

    fun setLocationDetails(context: Context?, location: String) {
        val sp = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val editor = sp?.edit()
        editor?.putString(
            PREF_LOCATION,
            location
        )

        editor?.apply()
    }

    fun setTempUnit(context: Context?, tempUnit: String) {
        val sp = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val editor = sp?.edit()
        editor?.putString(
            PREF_TEMP_UNIT,
            tempUnit
        )

        editor?.apply()
    }


    fun resetLocation(context: Context?) {
        val sp = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val editor = sp?.edit()
        editor?.remove(PREF_LOCATION)
        editor?.apply()
    }

    fun resetTempUnit(context: Context?) {
        val sp = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val editor = sp?.edit()
        editor?.remove(PREF_TEMP_UNIT)
        editor?.apply()
    }
}
package com.example.sunshine

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.preference.*
import com.example.sunshine.data.SunshinePreferences
import com.example.sunshine.viewmodel.WeatherForecastViewModel

class SettingsFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private val viewModel: WeatherForecastViewModel by viewModels()

    private fun setPreferenceSummary(preference: Preference, value: Any?) {
        val stringValue = value.toString()
        if (preference is ListPreference) {
            val listPreference: ListPreference = preference
            val prefIndex: Int = listPreference.findIndexOfValue(stringValue)
            if (prefIndex >= 0) {
                val summary = listPreference.entries[prefIndex]
                preference.setSummary(summary)
            }
        } else {
            preference.summary = stringValue
        }
    }

    override fun onCreatePreferences(bundle: Bundle?, s: String?) {
        addPreferencesFromResource(R.xml.pref_general)
        val sharedPreferences: SharedPreferences? = preferenceScreen.sharedPreferences
        val prefScreen: PreferenceScreen = preferenceScreen
        val count: Int = prefScreen.preferenceCount
        for (i in 0 until count) {
            val p: Preference = prefScreen.getPreference(i)
                val value = sharedPreferences?.getString(p.key, "")
                setPreferenceSummary(p, value)
            p.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
                    setPreferenceSummary(preference, newValue.toString())
                val activity: FragmentActivity? = activity
                if (preference.key == getString(R.string.pref_location_key)) {
                    SunshinePreferences().resetLocation(activity)
                    SunshinePreferences().setLocationDetails(activity, newValue.toString())
                    SunshinePreferences().getPreferredWeatherLocation(requireContext())
                        ?.let {
                            viewModel.setLocation(it) }

                } else if (preference.key == getString(R.string.pref_units_key)) {
                    SunshinePreferences().resetTempUnit(activity)
                    SunshinePreferences().setTempUnit(activity, newValue.toString())
                    SunshinePreferences().getPreferredWeatherTempUnit(requireContext())
                        ?.let {
                            viewModel.setTempUnit(it) }                }
                    true
                }

        }
    }

    override fun onStop() {
        super.onStop()
        preferenceScreen.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onStart() {
        super.onStart()
        preferenceScreen.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }


    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String?) {
        val preference: Preference? = findPreference(key!!)
        if (null != preference) {
            if (preference !is CheckBoxPreference) {
                setPreferenceSummary(preference, sharedPreferences.getString(key, ""))
            }
        }        }
}

package com.example.sunshine.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sunshine.model.WeatherForecast
import com.example.sunshine.utils.WeatherForecastApi
import kotlinx.coroutines.launch
import timber.log.Timber

class WeatherForecastViewModel: ViewModel() {
    private val _status = MutableLiveData<WeatherForecastUiState>()
    val status: LiveData<WeatherForecastUiState> = _status

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> = _location

    private val _tempUnit = MutableLiveData<String>()
    val tempUnit: LiveData<String> = _tempUnit

    private val WEATHER_API_KEY = "601200df17174c75aec193618232110"
    private val language = "pt"
    private val numDays = 14

    val QUERY_PARAM = "q"
    val KEY_PARAM = "key"
    val DAYS_PARAM = "days"
    val LANGUAGE_PARAM = "lang"

    fun setLocation(location: String){
        _location.value = location
    }

    fun setTempUnit(tempUnit: String){
        _tempUnit.value = tempUnit
    }

    init {
        _status.value = WeatherForecastUiState.Loading
        getWeatherForecast()
    }

    fun syncWeatherForecast(){
        getWeatherForecast()
    }

    private fun getWeatherForecast(){
        viewModelScope.launch{
            val params = HashMap<String, String>()
            params[KEY_PARAM] = WEATHER_API_KEY
            params[QUERY_PARAM] = location.value.toString()
            params[DAYS_PARAM] = numDays.toString()
            params[LANGUAGE_PARAM] = language
            try {
                val listResult = WeatherForecastApi.retrofitService.getWeatherForecast(
                    params
                )
                _status.value = WeatherForecastUiState.Success(listResult)
            } catch (e: Exception){
                _status.value = WeatherForecastUiState.Error("Failure: ${e.message}")
                Timber.e(e)
            }
        }
    }
}

sealed interface WeatherForecastUiState {
    data class Success(val list: WeatherForecast) : WeatherForecastUiState
    data class Error(val message: String) : WeatherForecastUiState
    object Loading : WeatherForecastUiState
}
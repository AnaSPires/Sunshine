package com.example.sunshine.utils

import com.example.sunshine.model.WeatherForecast
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.QueryMap
import java.util.*


private const val DYNAMIC_WEATHER_URL = "https://api.weatherapi.com/v1/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(DYNAMIC_WEATHER_URL)
        .build()

    interface WeatherForecastApiService{
        @GET("forecast.json")
        suspend fun getWeatherForecast(
            @QueryMap params: Map<String, String>
        ): WeatherForecast
    }

    object WeatherForecastApi{
        val retrofitService : WeatherForecastApiService by lazy {
            retrofit.create(WeatherForecastApiService::class.java) }
    }

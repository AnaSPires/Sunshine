package com.example.sunshine.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class WeatherForecast (
    @SerialName("location")
    val location: Location,
    @SerialName("current")
    val current: Current,
    @SerialName("forecast")
    val forecast: Forecast
): java.io.Serializable

@kotlinx.serialization.Serializable
data class Location(
    @SerialName("name")
    val name: String,
    @SerialName("region")
    val region: String,
    @SerialName("country")
    val country: String,
    @SerialName("lat")
    val latitude: Double,
    @SerialName("lon")
    val longitude: Double,
    @SerialName("tz_id")
    val timeZone: String,
    @SerialName("localtime_epoch")
    val localTimeUnix: Int,
    @SerialName("localtime")
    val localtime: String,

)

@kotlinx.serialization.Serializable
data class Current(
    @SerialName("last_updated_epoch")
    val lastUpdatedUnix: Int,
    @SerialName("last_updated")
    val lastUpdated: String,
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("wind_mph")
    val windSpeedMiles: Double,
    @SerialName("wind_kph")
    val windSpeedKm: Double,
    @SerialName("wind_degree")
    val windDirectionDegree: Int,
    @SerialName("wind_dir")
    val windDirection: String,
    @SerialName("pressure_mb")
    val pressureMb: Double,
    @SerialName("pressure_in")
    val pressureInches: Double,
    @SerialName("precip_mm")
    val precipitationMm: Double,
    @SerialName("precip_in")
    val precipitationInches: Double,
    @SerialName("humidity")
    val humidityPercentage: Int,
    @SerialName("cloud")
    val cloudCoverPercentage: Int,
    @SerialName("feelslike_c")
    val sensacaoTempC: Double,
    @SerialName("feelslike_f")
    val sensacaoTempF: Double,
    @SerialName("vis_km")
    val visibilityKm: Double,
    @SerialName("vis_miles")
    val visibilityMiles: Double,
    @SerialName("uv")
    val uvIndex: Double,
    @SerialName("gust_mph")
    val windGustMiles: Double,
    @SerialName("gust_kph")
    val windGustKm: Double
    )

@kotlinx.serialization.Serializable
data class Condition(
    @SerialName("text")
    val text: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("code")
    val code: Int
)

@kotlinx.serialization.Serializable
data class Forecast(
    @SerialName("forecastday")
    val forecastDay: List<ForecastDay>
)

@kotlinx.serialization.Serializable
data class ForecastDay(
    @SerialName("date")
    val date: String,
    @SerialName("date_epoch")
    val unixDate: Int,
    @SerialName("day")
    val day: Day,
    @SerialName("astro")
    val astro: Astro,
    @SerialName("hour")
    val hours: List<Hour>
    ): java.io.Serializable

@kotlinx.serialization.Serializable
data class Day(
    @SerialName("maxtemp_c")
    val maxTempC: Double,
    @SerialName("maxtemp_f")
    val maxTempF: Double,
    @SerialName("mintemp_c")
    val minTempC: Double,
    @SerialName("mintemp_f")
    val minTempF: Double,
    @SerialName("avgtemp_c")
    val avgTempC: Double,
    @SerialName("avgtemp_f")
    val avgTempF: Double,
    @SerialName("maxwind_mph")
    val maxWindSpeedMiles: Double,
    @SerialName("maxwind_kph")
    val maxWindSpeedKm: Double,
    @SerialName("avgvis_km")
    val avgVisibilityKm: Double,
    @SerialName("avgvis_miles")
    val avgVisibilityMiles: Double,
    @SerialName("totalprecip_mm")
    val totalPrecipitationMm: Double,
    @SerialName("totalprecip_in")
    val totalPrecipitationInches: Double,
    @SerialName("totalsnow_cm")
    val totalSnowCm: Double,
    @SerialName("avghumidity")
    val avgHumidityPercentage: Double,
    @SerialName("daily_will_it_rain")
    val dailyWillRain: Int,
    @SerialName("daily_will_it_snow")
    val dailyWillSnow: Int,
    @SerialName("daily_chance_of_rain")
    val dailyChanceRainPercentage: Int,
    @SerialName("daily_chance_of_snow")
    val dailyChanceSnowPercentage: Int,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("uv")
    val uvIndex: Double
)

@kotlinx.serialization.Serializable
data class Astro(
    @SerialName("sunrise")
    val sunriseTime: String,
    @SerialName("sunset")
    val sunsetTime: String,
    @SerialName("moonrise")
    val moonriseTime: String,
    @SerialName("moonset")
    val moonsetTime: String,
    @SerialName("moon_phase")
    val moonFase: String,
    @SerialName("moon_illumination")
    val moonIluminationPercentage: Int,
    @SerialName("is_moon_up")
    val isMoonUp: Int,
    @SerialName("is_sun_up")
    val isSunUp: Int
)

@kotlinx.serialization.Serializable
data class Hour(
    @SerialName("time_epoch")
    val timeUnix: Int,
    @SerialName("time")
    val time: String,
    @SerialName("temp_c")
    val tempC: Double,
    @SerialName("temp_f")
    val tempF: Double,
    @SerialName("is_day")
    val isDay: Int,
    @SerialName("condition")
    val condition: Condition,
    @SerialName("wind_mph")
    val windSpeedMiles: Double,
    @SerialName("wind_kph")
    val windSpeedKm: Double,
    @SerialName("wind_degree")
    val windDirectionDegree: Int,
    @SerialName("wind_dir")
    val windDirection: String,
    @SerialName("pressure_mb")
    val pressureMb: Double,
    @SerialName("pressure_in")
    val pressureInches: Double,
    @SerialName("precip_mm")
    val precipitationMm: Double,
    @SerialName("precip_in")
    val precipitationInches: Double,
    @SerialName("humidity")
    val humidityPercentage: Int,
    @SerialName("cloud")
    val cloudCoverPercentage: Int,
    @SerialName("feelslike_c")
    val sensacaoTempC: Double,
    @SerialName("feelslike_f")
    val sensacaoTempF: Double,
    @SerialName("windchill_c")
    val windChillTempC: Double,
    @SerialName("windchill_f")
    val windChillTempF: Double,
    @SerialName("heatindex_c")
    val heatIndexC: Double,
    @SerialName("heatindex_f")
    val heatIndexF: Double,
    @SerialName("dewpoint_c")
    val dewPointC: Double,
    @SerialName("dewpoint_f")
    val dewPointF: Double,
    @SerialName("will_it_rain")
    val willRain: Int,
    @SerialName("will_it_snow")
    val willSnow: Int,
    @SerialName("chance_of_rain")
    val chanceRainPercentage: Int,
    @SerialName("chance_of_snow")
    val chanceSnowPercentage: Int,
    @SerialName("vis_km")
    val visibilityKm: Double,
    @SerialName("vis_miles")
    val visibilityMiles: Double,
    @SerialName("uv")
    val uvIndex: Double,
    @SerialName("gust_mph")
    val windGustMiles: Double,
    @SerialName("gust_kph")
    val windGustKm: Double
)
package com.quangkhai.sampletest.practice.data.remote

// ============================================================================
// EXAM: Networking — Retrofit + Coroutines (15 pts)
// ----------------------------------------------------------------------------
// Retrofit interface for OpenWeatherMap current-weather endpoint.
//   - suspend getWeather(lat, lon, apiKey, units = "metric"): <DTO>
//   - annotate with @GET("weather") and @Query params
// Base URL (https://api.openweathermap.org/data/2.5/) is supplied by DI.
// ============================================================================

// import hints:
import com.quangkhai.sampletest.BuildConfig
import com.quangkhai.sampletest.practice.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String = BuildConfig.OPEN_WEATHER_MAP_API,
        @Query("units") units: String = "metric",
    ): WeatherDto
}
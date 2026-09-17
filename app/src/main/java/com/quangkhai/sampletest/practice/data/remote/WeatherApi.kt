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
import retrofit2.http.GET
import retrofit2.http.Query

package com.quangkhai.sampletest.practice.domain.repository

import com.quangkhai.sampletest.practice.domain.model.Weather

// ============================================================================
// EXAM: Architecture — Repository (10 pts) + Networking (15 pts)
// ----------------------------------------------------------------------------
// Interface fetching weather for a location.
//   - suspend getWeather(lat, lon): <domain Weather, wrapped in Result or similar>
//   - hide Retrofit/DTO behind this interface
// No framework imports needed (return your own domain type).
// ============================================================================

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, long: Double): Result<Weather>
}
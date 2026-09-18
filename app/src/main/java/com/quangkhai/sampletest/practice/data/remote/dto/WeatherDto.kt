package com.quangkhai.sampletest.practice.data.remote.dto

// ============================================================================
// EXAM: Networking — DTO + mapping (15 pts)
// ----------------------------------------------------------------------------
// @Serializable DTOs that MIRROR the OpenWeatherMap /data/2.5/weather JSON.
// The JSON is nested, so we need one class per level:
//
//   {
//     "coord":   { "lat": .., "lon": .. },        -> CoordDto
//     "weather": [ { "id":.., "main":..,          -> WeatherItemDto (array element)
//                    "description":.. } ],
//     "main":    { "temp": .. },                   -> MainDto
//     "name":    "..",
//     "id":      1566083                           -> city id (ignored)
//   }
//
// toDomain() flattens these into the clean domain Weather model.
// ============================================================================

import com.quangkhai.sampletest.practice.domain.model.Weather
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    val coord: CoordDto,
    val weather: List<WeatherItemDto>,
    val main: MainDto,
    val name: String,
)

@Serializable
data class CoordDto(
    val lat: Double,
    val lon: Double,
)

// One element of the "weather" array. This is where the WEATHER id lives
// (e.g. 800 = clear), NOT the top-level city id.
@Serializable
data class WeatherItemDto(
    val id: Int,
    val main: String,          // "Clear"
    val description: String,   // "clear sky"
)

@Serializable
data class MainDto(
    val temp: Double,          // 30.2
)

fun WeatherDto.toDomain(): Weather {
    val item = weather.firstOrNull()
    return Weather(
        id = item?.id ?: 0,
        lat = coord.lat,
        lon = coord.lon,
        weatherTag = item?.main ?: "Unknown",
        temp = main.temp,
        description = item?.description ?: "",
    )
}

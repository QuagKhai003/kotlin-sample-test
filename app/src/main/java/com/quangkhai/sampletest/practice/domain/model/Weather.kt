package com.quangkhai.sampletest.practice.domain.model


// ============================================================================
// EXAM: Networking — clean domain model (part of 15) + Theory Q1 (layer separation)
// ----------------------------------------------------------------------------
// Plain domain model the app actually uses (decoupled from the network DTO).
//   - fields: condition + temperature
//   - maybe a helper to format the note's weatherTag (e.g. "Sunny, 30°C")
// No framework imports needed.
// ============================================================================
data class Weather(
    val id: Int,
    val lat: Double,
    val lon: Double,
    val weatherTag: String,
    val temp: Double,
    val description: String,
)

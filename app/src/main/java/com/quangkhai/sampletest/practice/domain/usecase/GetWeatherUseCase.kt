package com.quangkhai.sampletest.practice.domain.usecase

import com.quangkhai.sampletest.practice.domain.model.Weather
import com.quangkhai.sampletest.practice.domain.repository.WeatherRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, long: Double): Result<Weather> =
        weatherRepository.getCurrentWeather(lat, long)
}

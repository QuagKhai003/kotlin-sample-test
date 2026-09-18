package com.quangkhai.sampletest.practice.data.repository

// ============================================================================
// EXAM: Architecture — Repository implementation (10) over Retrofit (15)
// ----------------------------------------------------------------------------
// Implement WeatherRepository using WeatherApi.
//   - @Inject constructor(api: WeatherApi)
//   - call the API inside try/catch, return Result (success/error)
//   - map DTO -> domain Weather
// ============================================================================

// import hints:
import com.quangkhai.sampletest.practice.domain.model.Weather
import com.quangkhai.sampletest.practice.domain.repository.WeatherRepository
import com.quangkhai.sampletest.practice.data.remote.WeatherApi
import com.quangkhai.sampletest.practice.data.remote.dto.toDomain
import kotlinx.coroutines.CancellationException
import retrofit2.HttpException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getCurrentWeather(
        lat: Double,
        long: Double
    ): Result<Weather> {
        return try {
            val dto = weatherApi.getCurrentWeather(lat = lat, lon = long)
            Result.success(dto.toDomain())
        } catch (err: CancellationException) {
            throw err
        } catch (err: HttpException) {
            Result.failure(Exception("Http errors: " + err.code()))
        } catch (err: Exception) {
            Result.failure(err)
        }
    }

}
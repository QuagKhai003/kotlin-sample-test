package com.quangkhai.sampletest.practice.di

// ============================================================================
// EXAM: Architecture — Hilt DI (10) + Theory Q5. Provide Retrofit dependencies.
// ----------------------------------------------------------------------------
// @Module @InstallIn(SingletonComponent). @Provides:
//   - Retrofit  (base url + converter: kotlinx-serialization Json or Gson)
//   - WeatherApi  (retrofit.create(...))
// ============================================================================

// import hints:
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

}
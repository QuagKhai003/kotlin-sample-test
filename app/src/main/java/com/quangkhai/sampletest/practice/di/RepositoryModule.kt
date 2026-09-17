package com.quangkhai.sampletest.practice.di

// ============================================================================
// EXAM: Architecture — Hilt DI (10) + Theory Q5. Bind repositories to impls.
// ----------------------------------------------------------------------------
// @Module @InstallIn(SingletonComponent).
//   - bind NoteRepository    -> NoteRepositoryImpl
//   - bind WeatherRepository -> WeatherRepositoryImpl
// Use @Binds (abstract module) OR @Provides (object module) — your choice.
// ============================================================================

// import hints:
import com.quangkhai.sampletest.practice.domain.repository.NoteRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Bind
    @Singleton
    fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}
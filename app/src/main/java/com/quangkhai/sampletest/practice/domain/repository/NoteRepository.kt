package com.quangkhai.sampletest.practice.domain.repository

// ============================================================================
// EXAM: Architecture — Repository (10 pts) + Theory Q1 (why a Repository layer)
// ----------------------------------------------------------------------------
// Interface abstracting note storage (the ViewModel depends on THIS, not Room).
//   - observe notes  -> Flow<List<NoteEntity>>
//   - get note by id
//   - add / update note
//   - delete note
// ============================================================================

// import hints:
import com.quangkhai.sampletest.practice.data.local.NoteEntity
import dagger.Module
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun getNoteById(noteId: String): Result<NoteEntity>

    suspend fun getAllNotes(): Result<List<NoteEntity>>
}

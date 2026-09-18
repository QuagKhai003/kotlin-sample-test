package com.quangkhai.sampletest.practice.data.repository

// ============================================================================
// EXAM: Architecture — Repository implementation (10) backed by Room (15)
// ----------------------------------------------------------------------------
// Implement NoteRepository using NoteDao.
//   - @Inject constructor(dao: NoteDao)  (Hilt provides the dao)
//   - delegate to the DAO; map entity <-> domain if you keep them separate
// ============================================================================

// import hints:
import com.quangkhai.sampletest.practice.data.local.NoteDao
import com.quangkhai.sampletest.practice.data.local.NoteEntity
import com.quangkhai.sampletest.practice.domain.repository.NoteRepository
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
): NoteRepository {
    override suspend fun getNoteById(noteId: String): Result<NoteEntity> {
        return try {
            val note = noteDao.getNoteById(noteId)
            if (note != null) Result.success(note)
            else Result.failure(Exception("No note with that id"))
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getAllNotes(): Result<List<NoteEntity>> {
        return try {
            val notes = noteDao.getAllNotes()
            Result.success(notes)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun addNote(note: NoteEntity): Result<Unit> {
        return try {
            noteDao.insertNote(note)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteNote(noteId: String): Result<Unit> {
        return try {
            noteDao.deleteByOrderId(noteId)
            Result.success(Unit)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}

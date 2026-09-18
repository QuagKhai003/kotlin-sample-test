package com.quangkhai.sampletest.practice.domain.usecase

import com.quangkhai.sampletest.practice.data.local.NoteEntity
import com.quangkhai.sampletest.practice.domain.repository.NoteRepository
import javax.inject.Inject

class GetNoteByIdUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepository
) {
    suspend operator fun invoke(noteId: String): Result<NoteEntity> {
        if (noteId.isBlank()) {
            return Result.failure(Exception("User id is empty"))
        }
        return noteRepositoryImpl.getNoteById(noteId)
    }
}
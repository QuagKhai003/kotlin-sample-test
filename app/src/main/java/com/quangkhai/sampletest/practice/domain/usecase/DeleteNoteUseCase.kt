package com.quangkhai.sampletest.practice.domain.usecase

import com.quangkhai.sampletest.practice.domain.repository.NoteRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(noteId: String): Result<Unit> {
        if (noteId.isBlank()) {
            return Result.failure(Exception("Note id is empty"))
        }
        return noteRepository.deleteNote(noteId)
    }
}

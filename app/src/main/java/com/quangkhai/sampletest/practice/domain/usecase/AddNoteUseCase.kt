package com.quangkhai.sampletest.practice.domain.usecase

import com.quangkhai.sampletest.practice.data.local.NoteEntity
import com.quangkhai.sampletest.practice.domain.repository.NoteRepository
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: NoteEntity): Result<Unit> {
        if (note.noteName.isBlank()) {
            return Result.failure(Exception("Note title is empty"))
        }
        return noteRepository.addNote(note)
    }
}

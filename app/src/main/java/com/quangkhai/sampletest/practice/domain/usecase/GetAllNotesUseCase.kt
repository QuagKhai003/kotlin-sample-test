package com.quangkhai.sampletest.practice.domain.usecase

import com.quangkhai.sampletest.practice.data.local.NoteEntity
import com.quangkhai.sampletest.practice.domain.repository.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val noteRepositoryImpl: NoteRepository
) {
    suspend operator fun invoke(): Result<List<NoteEntity>> = noteRepositoryImpl.getAllNotes()
}
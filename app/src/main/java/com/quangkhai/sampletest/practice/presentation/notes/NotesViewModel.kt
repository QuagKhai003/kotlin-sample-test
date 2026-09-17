package com.quangkhai.sampletest.practice.presentation.notes

// ============================================================================
// EXAM: MVVM ViewModel (part of 10) + Theory Q1 (data flow API -> UI)
// ----------------------------------------------------------------------------
// @HiltViewModel with @Inject constructor(noteRepo, weatherRepo, prefs).
//   - expose notes + UiState (loading/success/error) as StateFlow
//   - addNote(title, content, attachWeather: Boolean)
//   - deleteNote(id)
//   - fetchWeather(lat, lon) -> tag the note (rubric 3)
//   - toggle dark mode via PreferencesManager
// Run work in viewModelScope; set Loading -> Success/Error.
// ============================================================================

// import hints:
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quangkhai.sampletest.practice.data.local.NoteEntity
import com.quangkhai.sampletest.practice.domain.usecase.GetAllNotesUseCase
import com.quangkhai.sampletest.practice.domain.usecase.GetNoteByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
): ViewModel() {
    private val _noteList = MutableStateFlow<List<NoteEntity>>(emptyList())

    val noteList: StateFlow<List<NoteEntity>> = _noteList.asStateFlow()
    private val _note = MutableStateFlow<NoteEntity?>(null)

    val note: StateFlow<NoteEntity?> = _note.asStateFlow()
    private val _error = MutableStateFlow<String?>(null)

    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        viewModelScope.launch {
            getAllNotesUseCase()
                .onSuccess { _noteList.value = it }
                .onFailure { _error.value = "Couldn't load notes"}
        }
    }

    fun searchNote(noteId: String) {
        viewModelScope.launch {
           getNoteByIdUseCase(noteId)
               .onSuccess { _note.value = it }
               .onFailure { _error.value = "Not existed"}
//                   if (it is HttpException) {
//                       _error.value = "${it.code()} error"
//                   } else _error.value = "Unknown error"
        }
    }
}
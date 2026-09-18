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
import com.quangkhai.sampletest.practice.domain.usecase.AddNoteUseCase
import com.quangkhai.sampletest.practice.domain.usecase.DeleteNoteUseCase
import com.quangkhai.sampletest.practice.domain.usecase.GetAllNotesUseCase
import com.quangkhai.sampletest.practice.domain.usecase.GetNoteByIdUseCase
import com.quangkhai.sampletest.practice.domain.usecase.GetWeatherUseCase
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
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getWeatherUseCase: GetWeatherUseCase,
): ViewModel() {
    private val _noteList = MutableStateFlow<List<NoteEntity>>(emptyList())
    val noteList: StateFlow<List<NoteEntity>> = _noteList.asStateFlow()

    private val _note = MutableStateFlow<NoteEntity?>(null)
    val note: StateFlow<NoteEntity?> = _note.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    // loading flag (rubric 5: manage loading state)
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    // the fetched weather tag for the note being created, e.g. "Clear, 30°C"
    private val _weatherTag = MutableStateFlow<String?>(null)
    val weatherTag: StateFlow<String?> = _weatherTag.asStateFlow()

    init {
        viewModelScope.launch { refresh() }
    }

    // Re-read the list snapshot into state. Call after every mutation so the UI updates.
    private suspend fun refresh() {
        _isLoading.value = true
        getAllNotesUseCase()
            .onSuccess { _noteList.value = it }
            .onFailure { _error.value = "Couldn't load notes" }
        _isLoading.value = false
    }

    // rubric 3: fetch weather and expose the tag for the note being created.
    // Coordinates default to a fixed city; swap in device location (FusedLocation) if desired.
    fun fetchWeather(lat: Double = 10.7626, long: Double = 106.6602) {
        viewModelScope.launch {
            getWeatherUseCase(lat, long)
                .onSuccess { _weatherTag.value = "${it.weatherTag}, ${it.temp.toInt()}°C" }
                .onFailure { _error.value = "Couldn't fetch weather" }
        }
    }

    // reset the weather tag when opening a fresh note form
    fun clearWeather() {
        _weatherTag.value = null
    }

    fun addNote(note: NoteEntity) {
        viewModelScope.launch {
            addNoteUseCase(note)
                .onSuccess { refresh() }
                .onFailure { _error.value = "Couldn't add note" }
        }
    }

    fun deleteNote(noteId: String) {
        viewModelScope.launch {
            deleteNoteUseCase(noteId)
                .onSuccess { refresh() }
                .onFailure { _error.value = "Couldn't delete note" }
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
package com.quangkhai.sampletest.practice.presentation.notes

// ============================================================================
// EXAM: UI — Home screen (10, rubric 1)
// ----------------------------------------------------------------------------
// @Composable list of saved notes + FAB to add.
//   - collect UiState from the ViewModel
//   - LazyColumn of notes; show loading / empty / error states
//   - FAB -> onAddClick (navigate to Detail)
//   - tapping a note -> onNoteClick(id)
// ============================================================================

// import hints:
import android.R.attr.text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen () {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

    }
}
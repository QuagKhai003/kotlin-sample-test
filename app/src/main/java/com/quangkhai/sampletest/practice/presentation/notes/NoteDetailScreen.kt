package com.quangkhai.sampletest.practice.presentation.notes

// ============================================================================
// EXAM: UI — Note detail screen (10, rubric 1) + Attach Weather (rubric 3)
// ----------------------------------------------------------------------------
// Create a new note (or view an existing one). Stateless: state + callbacks in.
//   - title + content fields
//   - "Attach Weather" -> onAttachWeather(); the fetched tag is shown
//   - Save -> onSave(title, content); then caller navigates back
// ============================================================================

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.quangkhai.sampletest.practice.data.local.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen(
    existing: NoteEntity?,          // null = create new; non-null = view existing
    weatherTag: String?,            // freshly fetched tag (create mode)
    onAttachWeather: () -> Unit,
    onSave: (title: String, content: String, weatherTag: String?) -> Unit,
    onBack: () -> Unit,
) {
    val readOnly = existing != null
    var title by remember { mutableStateOf(existing?.noteName ?: "") }
    var content by remember { mutableStateOf(existing?.noteDetail ?: "") }

    // In view mode show the saved tag; in create mode show the just-fetched one.
    val shownTag = existing?.weatherTag ?: weatherTag

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (readOnly) "Note" else "New Note") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                readOnly = readOnly,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                readOnly = readOnly,
                modifier = Modifier.fillMaxWidth()
            )

            if (shownTag != null) {
                Text("Weather: $shownTag", style = MaterialTheme.typography.bodyMedium)
            }

            if (!readOnly) {
                OutlinedButton(
                    onClick = onAttachWeather,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (weatherTag == null) "Attach Weather Info" else "Refresh Weather")
                }
                Button(
                    onClick = { onSave(title, content, weatherTag) },
                    enabled = title.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save")
                }
            }
        }
    }
}

package com.quangkhai.sampletest.practice.presentation.navigation

// ============================================================================
// EXAM: UI & Navigation (10, rubric 1)
// ----------------------------------------------------------------------------
// NavHost wiring Home + Detail. Obtains the ViewModel with hiltViewModel(),
// collects its state, and passes state + callbacks into the stateless screens.
// ============================================================================

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.quangkhai.sampletest.practice.data.local.NoteEntity
import com.quangkhai.sampletest.practice.presentation.notes.HomeScreen
import com.quangkhai.sampletest.practice.presentation.notes.NoteDetailScreen
import com.quangkhai.sampletest.practice.presentation.notes.NotesViewModel

private const val NEW_NOTE = "new"

@Composable
fun NoteNavGraph(
    darkMode: Boolean,
    onToggleDark: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NoteNavRoute.HomeScreen.route,
) {
    // One VM shared across Home + Detail (nav-graph scope).
    val viewModel: NotesViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NoteNavRoute.HomeScreen.route) {
            val notes by viewModel.noteList.collectAsState()
            val isLoading by viewModel.isLoading.collectAsState()

            HomeScreen(
                notes = notes,
                isLoading = isLoading,
                darkMode = darkMode,
                onToggleDark = onToggleDark,
                onAddClick = {
                    navController.navigate(NoteNavRoute.DetailScreen.createRoute(NEW_NOTE))
                },
                onNoteClick = { id ->
                    navController.navigate(NoteNavRoute.DetailScreen.createRoute(id))
                },
                onDelete = { id -> viewModel.deleteNote(id) },
            )
        }

        composable(
            route = NoteNavRoute.DetailScreen.route,
            arguments = listOf(navArgument("noteId") { type = NavType.StringType }),
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: NEW_NOTE
            val notes by viewModel.noteList.collectAsState()
            val weatherTag by viewModel.weatherTag.collectAsState()

            val existing = if (noteId == NEW_NOTE) null else notes.find { it.noteId == noteId }

            // fresh form: clear any leftover weather tag
            LaunchedEffect(noteId) { viewModel.clearWeather() }

            NoteDetailScreen(
                existing = existing,
                weatherTag = weatherTag,
                onAttachWeather = { viewModel.fetchWeather() },
                onSave = { title, content, tag ->
                    viewModel.addNote(
                        NoteEntity(noteName = title, noteDetail = content, weatherTag = tag)
                    )
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() },
            )
        }
    }
}

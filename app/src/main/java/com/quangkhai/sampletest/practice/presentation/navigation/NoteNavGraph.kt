package com.quangkhai.sampletest.practice.presentation.navigation

// ============================================================================
// EXAM: UI & Navigation (10, rubric 1)
// ----------------------------------------------------------------------------
// @Composable NavHost wiring the screens.
//   - startDestination = Home
//   - Home   : notes list + FAB -> navigate to Detail (new note)
//   - Detail : create/view a note, "Attach Weather" action
//   - obtain the ViewModel with hiltViewModel()
// ============================================================================

// import hints:
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.quangkhai.sampletest.practice.presentation.notes.HomeScreen

@Composable
fun NoteNavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NoteNavRoute.HomeScreen.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NoteNavRoute.HomeScreen.route) {
            HomeScreen()
        }
    }
}
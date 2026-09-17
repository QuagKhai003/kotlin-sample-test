package com.quangkhai.sampletest.practice.presentation.navigation

// ============================================================================
// EXAM: UI & Navigation — routes (10, rubric 1)
// ----------------------------------------------------------------------------
// Type-safe route definitions (sealed class of screens).
//   - Home   -> "home"
//   - Detail -> "detail/{noteId}" with a createRoute(noteId) helper
//     (reuse Detail for both "create new" and "view existing")
// No framework imports needed.
// ============================================================================
sealed class NoteNavRoute (val route: String) {
    data object HomeScreen : NoteNavRoute("homescreen")
    data object DetailScreen: NoteNavRoute("detail/{noteId}") {
        fun createRoute(noteId: String) = "detail/$noteId"
    }
}
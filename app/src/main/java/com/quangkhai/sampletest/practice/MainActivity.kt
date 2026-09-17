package com.quangkhai.sampletest.practice

// ============================================================================
// EXAM: UI & Navigation (10) + DataStore dark mode (part of 15) + Theory Q2 (Activity lifecycle)
// ----------------------------------------------------------------------------
// Single-activity entry point that hosts the Compose UI.
//  - ComponentActivity, annotate @AndroidEntryPoint (Hilt)
//  - setContent { } : wrap the app in MaterialTheme
//  - read dark-mode flag from DataStore and switch light/dark colorScheme
//  - create a NavController and host the navigation graph
// ============================================================================

// import hints:
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.navigation.compose.rememberNavController
import com.quangkhai.sampletest.practice.presentation.notes.HomeScreen
import com.quangkhai.sampletest.tutorial03.presentation.task.TaskNavGraph
import com.quangkhai.sampletest.tutorial03.presentation.task.TaskViewModel
import com.quangkhai.sampletest.tutorial03.ui.theme.Tutorial03Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeScreen()
        }
    }
}

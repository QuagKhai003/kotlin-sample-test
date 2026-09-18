package com.quangkhai.sampletest.practice

// ============================================================================
// EXAM: UI & Navigation (10) + DataStore dark mode (part of 15)
//       + schedule reminder Worker (rubric 4) + Theory Q2 (Activity lifecycle)
// ----------------------------------------------------------------------------
// Single-activity entry point:
//  - @AndroidEntryPoint (Hilt)
//  - read dark-mode flag from DataStore -> pick light/dark colorScheme
//  - host the navigation graph
//  - request POST_NOTIFICATIONS (API 33+) and schedule the periodic reminder
// ============================================================================

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.quangkhai.sampletest.practice.data.preferences.PreferencesManager
import com.quangkhai.sampletest.practice.presentation.navigation.NoteNavGraph
import com.quangkhai.sampletest.practice.work.Notify
import com.quangkhai.sampletest.practice.work.ReminderWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestNotifications =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // notifications: channel + runtime permission (API 33+)
        Notify.ensureChannel(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestNotifications.launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        // rubric 4: schedule the periodic 24h reminder check (unique, no duplicates)
        val reminder = PeriodicWorkRequestBuilder<ReminderWorker>(6, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "note-reminder",
            ExistingPeriodicWorkPolicy.UPDATE,
            reminder,
        )

        setContent {
            val darkMode by PreferencesManager.getDarkMode(this).collectAsState(initial = false)
            val scope = rememberCoroutineScope()

            MaterialTheme(
                colorScheme = if (darkMode) darkColorScheme() else lightColorScheme()
            ) {
                NoteNavGraph(
                    darkMode = darkMode,
                    onToggleDark = { enabled ->
                        scope.launch { PreferencesManager.saveDarkMode(this@MainActivity, enabled) }
                    },
                )
            }
        }
    }
}

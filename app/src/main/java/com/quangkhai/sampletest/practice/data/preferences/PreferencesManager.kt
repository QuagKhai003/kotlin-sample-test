package com.quangkhai.sampletest.practice.data.preferences

// ============================================================================
// EXAM: Data Persistence — DataStore (part of 15) + Theory Q3 (DataStore vs Room)
// ----------------------------------------------------------------------------
// Preferences DataStore for small key-value settings (dark mode on/off).
//   - expose darkMode as Flow<Boolean>
//   - suspend setDarkMode(enabled: Boolean)
//   - make it injectable (@Inject constructor / @Singleton) so Hilt provides it
// NoteEntity (Q3): DataStore = small prefs; Room = the notes list. Don't store lists here.
// ============================================================================

// import hints:
import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Create DataStore instance (scoped to application context)
val Context.dataStore by preferencesDataStore("settings")

// Preference Keys
private val DARK_MODE = booleanPreferencesKey("dark_mode")

object PreferencesManager {
    // Save Dark Mode preference
    suspend fun saveDarkMode(context: Context, enabled: Boolean) {
        context.dataStore.edit { settings ->
            settings[DARK_MODE] = enabled
        }
    }

    // Observe Dark Mode preference as a Flow
    fun getDarkMode(context: Context): Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[DARK_MODE] ?: false
        }
}
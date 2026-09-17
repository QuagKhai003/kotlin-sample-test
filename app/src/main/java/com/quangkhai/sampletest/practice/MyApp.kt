package com.quangkhai.sampletest.practice

// ============================================================================
// EXAM: Architecture & Good Practices (10 pts) + Theory Q5 (Hilt DI)
// ----------------------------------------------------------------------------
// Build the Application class that bootstraps Hilt's app-level container.
//  - annotate with @HiltAndroidApp
//  - register as android:name in AndroidManifest.xml so injection works at runtime
// ============================================================================

// import hints:
import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application()

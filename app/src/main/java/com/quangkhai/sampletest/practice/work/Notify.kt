package com.quangkhai.sampletest.practice.work

// ============================================================================
// EXAM: Notifications (part of 10, rubric 4) + Theory Q4
// ----------------------------------------------------------------------------
// Notification helper.
//   - create a NotificationChannel (needed API 26+)
//   - post a notification with the reminder text:
//       "Haven't written a note today? Add one now!"
//   - remember the POST_NOTIFICATIONS runtime permission on Android 13+
// ============================================================================

// import hints:
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

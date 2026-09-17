package com.quangkhai.sampletest.practice.work

// ============================================================================
// EXAM: Background Work — WorkManager (10, rubric 4) + Theory Q4
// ----------------------------------------------------------------------------
// A Worker (use CoroutineWorker for suspend Room calls). In doWork():
//   - read the latest note timestamp from Room
//   - if no note was created in the last 24h -> post the reminder via Notify
//   - return Result.success()
// Then schedule a PeriodicWorkRequest (every few hours) from MyApp / MainActivity.
// Theory Q4: a Worker (not a BroadcastReceiver) because this is deferrable,
// guaranteed background work that must survive process death.
// ============================================================================

// import hints:
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

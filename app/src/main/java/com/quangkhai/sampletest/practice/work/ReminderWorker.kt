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
import android.Manifest
import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.quangkhai.sampletest.practice.data.local.AppDatabase


class ReminderWorker (
    ctx: Context, params: WorkerParameters
): CoroutineWorker(ctx, params) {
    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override suspend fun doWork(): Result {
        val dao = AppDatabase.getDatabase(applicationContext).noteDao()
        val latestNoteTimeStamp = dao.getLatestNoteTimeStamp() ?: 0L
        val dayMillis = 24L * 60 * 60 * 1000

        if (System.currentTimeMillis() - latestNoteTimeStamp >= dayMillis) {
            Notify.ensureChannel(applicationContext)
            Notify.show(
                applicationContext,
                "Haven't written a note in 24 hours?",
                "Add one now!",
            )
        }
        return Result.success()
    }

}

package com.quangkhai.sampletest.practice.data.local

// ============================================================================
// EXAM: Data Persistence — Room Database (15 pts)
// ----------------------------------------------------------------------------
// abstract RoomDatabase.
//   - @Database(entities = [NoteEntity], version = 1)
//   - abstract fun noteDao(): NoteDao
//   - (optional) companion getDatabase(context) singleton so the WorkManager
//     Worker (rubric 4) can reach Room without Hilt injection
// ============================================================================

// import hints:
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        NoteEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {
        // Volatile ensures the instance is visible to all threads immediately
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            // Return existing instance if exists
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

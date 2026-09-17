package com.quangkhai.sampletest.practice.data.local

// ============================================================================
// EXAM: Data Persistence — Room (15 pts)
// ----------------------------------------------------------------------------
// Room @Entity data class "NoteEntity".
// Required fields (from spec):
//   - id (primary key, auto-generated)
//   - title
//   - content
//   - timestamp
//   - weatherTag  (OPTIONAL / nullable — set only when user attaches weather)
// ============================================================================

// import hints:
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "notes"
)
data class NoteEntity (
    @PrimaryKey val noteId: String = UUID.randomUUID().toString(),
    val noteName: String,
    val noteDetail: String,
    val timestamp: Long = System.currentTimeMillis(),
    val weatherTag: String? = null
)

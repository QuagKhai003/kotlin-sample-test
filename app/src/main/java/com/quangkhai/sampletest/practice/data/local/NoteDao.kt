package com.quangkhai.sampletest.practice.data.local

// ============================================================================
// EXAM: Data Persistence — Room DAO (15 pts)
// ----------------------------------------------------------------------------
// @Dao interface. Operations the app needs:
//   - observe all notes  -> Flow<List<NoteEntity>>   (Home list, reactive)
//   - get a note by id
//   - insert / upsert a note
//   - delete a note
//   - latest note timestamp  (used by the 24h reminder check, rubric 4)
// ============================================================================

// import hints:
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface NoteDao {
    @Query(value = "SELECT * FROM notes WHERE noteId = :noteId")
    suspend fun getNoteById(noteId: String): NoteEntity?

    @Query(value = "SELECT * FROM notes")
    suspend fun  getAllNotes(): List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("DELETE FROM notes WHERE noteId = :noteId")
    suspend fun deleteByOrderId(noteId: String)
}
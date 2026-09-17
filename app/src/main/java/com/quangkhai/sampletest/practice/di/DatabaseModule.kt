package com.quangkhai.sampletest.practice.di

// ============================================================================
// EXAM: Architecture — Hilt DI (10) + Theory Q5. Provide Room dependencies.
// ----------------------------------------------------------------------------
// @Module @InstallIn(SingletonComponent). @Provides:
//   - NoteDatabase  (Room.databaseBuilder, @ApplicationContext)
//   - NoteDao       (db.noteDao())
// ============================================================================

// import hints:
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java
import com.quangkhai.sampletest.practice.data.local.AppDatabase
import com.quangkhai.sampletest.practice.data.local.NoteDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "note_db").build()
    }

    @Provides
    @Singleton
    fun provideNoteDao(db: AppDatabase) : NoteDao = db.noteDao()
}

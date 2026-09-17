# Practice — "Personal Notes with Weather Tag"

Blank skeletons for the **SampleFinalExam** programming task. Every file has only:
`package` + import hints + a comment block of the requirement. **You write all the code.**

Package: `com.quangkhai.sampletest.practice` (separate from lessons — no clashes).

## File map → exam rubric

| Rubric (pts) | Files |
|---|---|
| 1. UI & Navigation (10) | `presentation/navigation/NoteNavRoute.kt`, `NoteNavGraph.kt`, `presentation/notes/HomeScreen.kt`, `NoteDetailScreen.kt` |
| 2. Room + DataStore (15) | `data/local/Note.kt`, `NoteDao.kt`, `NoteDatabase.kt`, `data/preferences/PreferencesManager.kt` |
| 3. Networking Retrofit (15) | `data/remote/WeatherApi.kt`, `data/remote/dto/WeatherResponse.kt`, `domain/model/Weather.kt`, `domain/repository/WeatherRepository.kt`, `data/repository/WeatherRepositoryImpl.kt` |
| 4. Background + Notify (10) | `work/ReminderWorker.kt`, `work/Notify.kt` |
| 5. Architecture / Hilt / state (10) | `MyApp.kt`, `MainActivity.kt`, `di/DatabaseModule.kt`, `NetworkModule.kt`, `RepositoryModule.kt`, `domain/repository/NoteRepository.kt`, `data/repository/NoteRepositoryImpl.kt`, `presentation/notes/NotesViewModel.kt`, `NotesUiState.kt` |

## Suggested build order

1. `Note` → `NoteDao` → `NoteDatabase` (Room works)
2. `NoteRepository` + impl → `DatabaseModule` (Hilt provides Room)
3. `NotesUiState` → `NotesViewModel` → `HomeScreen` + `NoteDetailScreen` → `NoteNavRoute` + `NoteNavGraph` → `MainActivity` (list + add flow runs)
4. `Weather` → `WeatherResponse` → `WeatherApi` → `WeatherRepository` + impl → `NetworkModule` → wire "Attach Weather"
5. `PreferencesManager` → dark-mode toggle
6. `Notify` → `ReminderWorker` → schedule PeriodicWork
7. `MyApp` @HiltAndroidApp last; `RepositoryModule` binds interfaces

## Run it

1. Set `MyApp` as `android:name` and `MainActivity` as launcher in `AndroidManifest.xml`.
2. Add `INTERNET` + `POST_NOTIFICATIONS` permissions.
3. OpenWeatherMap free API key at runtime (put in `local.properties` / `BuildConfig`, not committed).

## Reference lessons (peek only if stuck)

Room+DataStore+Hilt → `lecture06` · Retrofit → `restful` · Nav+MVVM → `tutorial03` ·
WorkManager+Notify → `tutorial08` · Hilt VM + UiState → `lecture11` · location lat/lon → `tutorial05`.

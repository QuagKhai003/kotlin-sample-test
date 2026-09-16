# Revision Path — Final Exam Prep

Maps the **SampleFinalExam** to the lessons copied into `app/src/main/java/com/quangkhai/sampletest/`.
Sample programming task: **"Personal Notes with Weather Tag"** (Compose + Room + DataStore + Retrofit + WorkManager + Hilt).

Every lesson lives under its own package. Set any lesson's `MainActivity` as the launcher in
`AndroidManifest.xml` (swap `.name`) to run and inspect it.

---

## Lesson inventory

| Lesson | Package | Teaches |
|--------|---------|---------|
| `tutorial03` | `.tutorial03` | Clean MVVM (domain/data/presentation), Navigation, use-cases, fake repository |
| `tutorial05` | `.tutorial05` | Retrofit REST + location + Google Map (map needs runtime key — see notes) |
| `tutorial08` | `.tutorial08` | WorkManager + Notifications + BroadcastReceivers (all three background tools) |
| `tutorial09` | `.tutorial09` | Foreground Service (media) + WorkManager download + ViewModel across rotation |
| `tutorial10` | `.tutorial10` | CameraX + ML Kit on-device image labeling / scan |
| `lecture06` | `.lecture06` | Room DB + DataStore preferences + Hilt DI (`@HiltAndroidApp` = launcher) |
| `lecture11` | `.lecture11` | Login flow, UseCase, `@HiltViewModel`, fake repo, UiState (loading/success/error) |
| `restful` | `.restful` | Full CRUD REST app: Retrofit + kotlinx.serialization + Nav + ViewModel state |

---

## Theory (40) → where to revise

- **Q1 MVVM / data flow API→UI / error+loading / no-Repository** → `restful` (API→VM→UI end to end),
  `tutorial03` (layer separation, why Repository), `lecture11` (`LoginUiState` loading/success/error).
- **Q2 Activity lifecycle / video player / persist across rotation** → `tutorial09` (ViewModel survives
  rotation, foreground service keeps playback alive). Playback position lives in the ViewModel, not the Activity.
- **Q3 DataStore vs Room / misuse / SharedPreferences→DataStore migration** → `lecture06`
  (`PreferencesManager` = DataStore for dark-mode flag; `Task`/`TaskDao`/`AppDatabase` = Room for the list).
- **Q4 WorkManager vs Foreground Service vs BroadcastReceiver** → `tutorial08` (`SummaryWorker`, `Notify`,
  `SystemEventReceiver`/`CustomEventReceiver` side by side), `tutorial09` (`MusicPlayerService` foreground +
  `DownloadWorker`). Receiver for long work fails: process dies after `onReceive` returns → hand off to a Worker.
- **Q5 Hilt DI / inject Retrofit into Repository / no-DI scaling** → `lecture06` (`DatabaseModule` `@Provides`,
  `MyApp` `@HiltAndroidApp`), `lecture11` (`@HiltViewModel`). Model the Retrofit `@Provides` on `DatabaseModule`.

---

## Programming (60) → assemble from these pieces

Build **"Personal Notes with Weather Tag"** by combining:

1. **UI & Navigation (10)** — copy the nav skeleton from `tutorial03`
   (`presentation/task/navigation/NavGraph.kt` + routes, Home list, Add/Detail screens, FAB).
2. **Room + DataStore (15)** — copy `lecture06` `Task`→`Note` entity (`id, title, content, timestamp,
   weatherTag`), `TaskDao`, `AppDatabase`, and `PreferencesManager` for the dark-mode flag.
3. **Networking (15)** — copy `restful` Retrofit setup (`ApiClient`, `StudentApi`→`WeatherApi`, DTO +
   `toDomain`, repository returning `Result`/Flow). Point it at OpenWeatherMap. `tutorial05` shows
   using device location to build the request.
4. **Background + Notifications (10)** — copy `tutorial08` `SummaryWorker` + `Notify`; schedule a periodic
   check, notify if no note in 24h.
5. **Architecture (10)** — wire it with Hilt like `lecture06`/`lecture11`: `@HiltAndroidApp` app,
   `@Provides` modules for Room + Retrofit, `@HiltViewModel`, UiState with loading/success/error.

### Suggested study order (fastest → build up)
`tutorial03` (MVVM shape) → `lecture06` (Room+DataStore+Hilt) → `restful` (Retrofit CRUD) →
`tutorial08` (WorkManager+notifications) → `lecture11` (Hilt VM + UiState) →
`tutorial09` (service/lifecycle, Q2) → `tutorial05`/`tutorial10` (location/camera extras).

---

## Notes on excluded / conditional libraries

- **Firebase**: removed entirely (not used).
- **External-API tools**: dropped. No Firebase, no secrets-gradle plugin, no Google Directions.
- **Google Maps** (`tutorial05` map screen): kept so the project compiles. `LatLng` and the SDK build
  **without a key**. To see actual map tiles at runtime, add `MAPS_API_KEY=...` to `local.properties`
  and a `com.google.android.geo.API_KEY` meta-data tag in the manifest. Without it: blank map, app still runs.
  This is the only lesson touching an external, keyed API — everything else is on-device or plain REST.
- **ML Kit** (`tutorial10`): on-device bundled models, **no key, no external API** — kept.
- **Location** (`play-services-location`): on-device GPS, no key — kept.
- **Retrofit**: plain REST client; the exam's OpenWeatherMap call needs a free API key at runtime only.

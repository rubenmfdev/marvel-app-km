
# Marvel App KMM

Marvel App built using **Kotlin Multiplatform Mobile (KMM)**, targeting **iOS** and **Android**, following **Clean Architecture** and **Unidirectional Data Flow (UDF)**. This project reimplements the original [marvel-app-swiftui](https://github.com/rubenmfdev/marvel-app-swiftui) using Kotlin Multiplatform to share logic between platforms.

> 📱 Powered by Kotlin Multiplatform, Jetpack Compose, SwiftUI, Ktor, SQLDelight, and more.

---

## 🧠 Architecture

The project is organized based on **Clean Architecture**, separating concerns into distinct layers:

```
shared/
├── domain/        # Business logic, models, and use cases
├── data/          # Repository implementations, API, and database
├── presentation/  # ViewModels, UI state, events (UDF/MVI)
androidApp/        # Android-specific UI with Jetpack Compose
iosApp/            # iOS-specific UI with SwiftUI
```

- **Domain Layer**: Contains platform-agnostic business logic (entities, use cases).
- **Data Layer**: Provides implementations for repositories using Ktor (network) and SQLDelight (storage).
- **Presentation Layer**: Shared ViewModels exposing `StateFlow` for state management.
- **UI Layer**: Native UIs built using Jetpack Compose (Android) and SwiftUI (iOS).

---

## 🚀 Features

- List of Marvel characters with thumbnails and names
- Character detail view
- Network request handling via shared Ktor client
- Shared business logic with platform-native UIs
- Offline cache with SQLDelight
- Reactive state management using StateFlow
- Dependency injection via Koin

---

## 🧰 Tech Stack

### ✅ Shared (Kotlin Multiplatform)

- [Kotlin Multiplatform](https://kotlinlang.org/lp/multiplatform/)
- [Ktor](https://ktor.io) – HTTP client
- [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) – JSON parsing
- [SQLDelight](https://cashapp.github.io/sqldelight/) – Multiplatform persistence
- [Koin](https://insert-koin.io) – Dependency injection
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) – Asynchronous programming
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) – Reactive state

### ✅ Android

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Coil](https://coil-kt.github.io/coil/) – Image loading
- Material 3 design

### ✅ iOS

- [SwiftUI](https://developer.apple.com/xcode/swiftui/)
- StateFlow <-> Combine bridge for reactive UI
- Integrated via shared Kotlin framework

---

## 🧪 Testing

- Shared tests with `kotlin-test`
- Coroutine & Flow testing using [Turbine](https://github.com/cashapp/turbine)
- Expect/actual mocks for platform-specific APIs

---

## 🔐 Marvel API Key Setup

You’ll need a free API key from [Marvel Developer Portal](https://developer.marvel.com/).

Create a `secrets.properties` file in the project root:

```properties
MARVEL_PUBLIC_KEY=your_public_key
MARVEL_PRIVATE_KEY=your_private_key
```

This file is ignored in version control for security.

---

## ⚙️ Getting Started

### Prerequisites

- Android Studio Giraffe or newer with Kotlin Multiplatform plugin
- Xcode 15+
- Kotlin 1.9.21+
- CocoaPods installed (for iOS interop)

### Clone the Project

```bash
git clone https://github.com/rubenmfdev/marvel-app-km.git
cd marvel-app-km
./gradlew sync
```

### Run on Android

- Open `androidApp/` in Android Studio
- Run on an emulator or device

### Run on iOS

- Open `iosApp/` in Xcode
- Select a simulator and run the app

> If you use CocoaPods: run `pod install` inside `iosApp/` first

---

## 📦 Project Structure

| Module        | Description                                    |
|---------------|------------------------------------------------|
| `shared`      | Common Kotlin module with business logic       |
| `androidApp`  | Native Android app using Jetpack Compose       |
| `iosApp`      | Native iOS app using SwiftUI                   |

---

## 🎯 Roadmap

- [x] Setup shared domain and use cases
- [x] Fetch character data via Marvel API
- [x] Create shared ViewModel and state flow
- [x] Display data in Jetpack Compose and SwiftUI
- [ ] Add search functionality
- [ ] Add favorites with offline support
- [ ] Add unit & UI tests for both platforms

---

## 🙏 Credits

- Original project: [marvel-app-swiftui](https://github.com/rubenmfdev/marvel-app-swiftui)
- API: [Marvel Developer API](https://developer.marvel.com/)

---

## 📄 License

This project is licensed under the MIT License. See the [LICENSE](./LICENSE) file for details.

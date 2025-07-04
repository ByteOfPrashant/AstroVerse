# AstroVerse 🌌

AstroVerse is a Kotlin Multiplatform (KMP) application that brings NASA's Astronomy Picture of the
Day (APOD) to your fingertips. Built with modern Android and iOS architecture, this app showcases
stunning astronomical images and educational content from NASA's vast collection.

## Features ✨

- 📸 **Daily Astronomy Pictures**: Browse NASA's Astronomy Picture of the Day
- 📱 **Cross-Platform**: Native Android and iOS applications from shared codebase
- 🎨 **Modern UI**: Beautiful Compose Multiplatform interface
- 🌐 **Offline-Ready**: Clean architecture with proper error handling

## Architecture 🏗️

The app follows Clean Architecture principles with clear separation of concerns:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Presentation  │    │     Domain      │    │      Data       │
│                 │    │                 │    │                 │
│ • UI Components │◄──►│ • Use Cases     │◄──►│ • Repositories  │
│ • ViewModels    │    │ • Models        │    │ • API Service   │
│ • Compose UI    │    │ • Repository    │    │ • DTOs          │
│                 │    │   Interfaces    │    │ • Remote/Local  │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Layer Breakdown:

- **📱 Presentation Layer** (`composeApp/`): Compose UI, ViewModels, and user interactions
- **🎯 Domain Layer** (`domain/`): Business logic, use cases, and domain models
- **🔌 Data Layer** (`data/`): Repository implementations, API services, and data sources

## Third-Party Libraries 📚

### Networking

- **[Ktor Client](https://ktor.io/docs/getting-started-ktor-client.html)** `v2.3.11`: HTTP client
  for API communication
  - Content negotiation with JSON serialization
  - Logging for debugging
  - Platform-specific implementations (Android/Darwin)

### Dependency Injection

- **[Koin](https://insert-koin.io/)** `v4.1.0`: Lightweight DI framework
  - `koin-core`: Core DI functionality
  - `koin-compose`: Integration with Compose
  - `koin-compose-viewmodel`: ViewModel injection

### Image Loading

- **[Coil](https://coil-kt.github.io/coil/)** `v2.7.0`: Efficient image loading for Compose
  - Async image loading with caching
  - Cross-platform image handling
  - Memory and disk caching

### Other Libraries

- **Compose Multiplatform** `v1.8.2`: UI toolkit for cross-platform development
- **Androidx Lifecycle** `v2.9.1`: ViewModel and lifecycle management
- **Kotlinx Serialization**: JSON parsing and serialization

## Project Structure 📁

```
AstroVerse/
├── composeApp/           # Shared UI and presentation logic
│   └── src/
│       ├── commonMain/   # Shared Compose UI
│       ├── androidMain/  # Android-specific code
│       └── iosMain/      # iOS-specific code
├── domain/               # Business logic and use cases
│   └── src/commonMain/
│       ├── model/        # Domain models
│       ├── repository/   # Repository interfaces  
│       ├── usesCases/    # Business use cases
│       └── di/           # Domain DI modules
├── data/                 # Data layer implementation
│   └── src/commonMain/
│       ├── remote/       # API services and networking
│       ├── repository/   # Repository implementations
│       ├── model/        # Data transfer objects
│       └── di/           # Data DI modules
└── iosApp/               # iOS application entry point
```

## Setup Instructions 🚀

### Prerequisites

- **Android Studio** (latest version)
- **Xcode** (for iOS development)
- **JDK 17** or higher

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/AstroVerse.git
cd AstroVerse
```

### 2. 🔑 Configure NASA API Key

**Important**: You need to replace the API key in the ApiService:

1. Get your free API key from [NASA API Portal](https://api.nasa.gov/)
2. Open `data/src/commonMain/kotlin/com/byteofprashant/data/remote/ApiService.kt`
3. Replace `"key"` with your actual API key:
   ```kotlin
   parameter("api_key", "YOUR_ACTUAL_API_KEY_HERE")
   ```

### 3. Build and Run

#### Android

```bash
./gradlew :composeApp:assembleDebug
```

#### iOS

Open `iosApp/iosApp.xcodeproj` in Xcode and run the project.

## String Resources 🌐

The app uses Compose Multiplatform's string resources for internationalization. All strings are
centralized in:

- **Location**: `composeApp/src/commonMain/composeResources/values/strings.xml`
- **Usage**: Access via `Res.string.resource_name` in Compose

## License 📄

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments 🙏

- **NASA** for providing the amazing APOD API
- **JetBrains** for Kotlin Multiplatform and Compose Multiplatform
- **The open-source community** for the excellent libraries used in this project

---

**Built with ❤️ using Kotlin Multiplatform**

# GithubPOC 🧞‍

## Features 🕹

- 100% Kotlin-only template
- Following [Clean Architecture approach](https://proandroiddev.com/mvvm-with-clean-architecture-c2c021e05c89)
- Following MVVM Architectural Design Pattern
- Template Project
- [RxJava 3](https://github.com/ReactiveX/RxJava) - Reactive Extensions for the JVM
- [Coroutines](https://developer.android.com/kotlin/coroutines) - A concurrency design pattern library
- [Flow](https://developer.android.com/kotlin/flow) - Built on top of coroutines and is stream of data that can be computed asynchronously
- Simplest Adapter Ever (based on this [workaround](https://proandroiddev.com/the-best-android-recycler-adapter-youve-ever-seen-probably-177e25279a28))
- Github Actions - CI
- [Hilt](https://dagger.dev/hilt/) - Dependency Injection framework
- [Transition](https://developer.android.com/guide/navigation/navigation-animate-transitions) - Animation
- [Paging V3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) - Pagination
- [View Binding](https://developer.android.com/topic/libraries/view-binding) - View Binding
- [OkHttp3](https://github.com/square/okhttp) - Network interceptor
- [Retrofit](https://github.com/square/retrofit) - HTTP client
- [Glide](https://github.com/bumptech/glide) - Loading images
- [Timber](https://github.com/JakeWharton/timber) - Log
- [Gson](https://github.com/google/gson) - JSON library
- [Material Components](https://github.com/material-components/material-components-android) - Material Design
- [Lottie](https://airbnb.design/lottie/) - Vector animation library
- [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Alternative syntax to the Groovy DSL
- [Detekt](https://github.com/detekt/detekt) - Static code analysis for Kotlin
- [Gradle Doctor](https://github.com/runningcode/gradle-doctor) - Gradle build scan plugin
- [Navigation](https://developer.android.com/guide/navigation) - Navigate through the app
- [LeakCanary](https://square.github.io/leakcanary/) - Memory leak detection
- [Chucker](https://github.com/ChuckerTeam/chucker) - An HTTP inspector for Android & OkHTTP
- [StrictMode](https://developer.android.com/reference/android/os/StrictMode) - A developer tool which detects things you might be doing by accident
- [Dark/Light Theme](https://developer.android.com/guide/topics/ui/look-and-feel/darktheme) - Support dark/light themes
- [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Data storage solution that allows you to store key-value pairs or typed objects
- [AutoDispose](https://uber.github.io/AutoDispose/) - Automatically binding the execution of RxJava streams to a provided scope

## CI 🏭

This template is using [**GitHub Actions**](https://github.com/Drjacky/MVVMTemplate/actions) as CI.

Available workflows listed as follows:
- [Validate Gradle Wrapper](.github/workflows/gradlew-validation.yml) - Checks the gradle wrapper has a valid checksum.
- [Pre Merge Checks](.github/workflows/pre-merge.yml) - Runs `build` task. 
- [Android](.github/workflows/android.yml) - Runs `assembleDebug` task.

## Tasks 🔧

- Gradle Doctor: `dependencyUpdates` -  Displays the dependency updates for the project.
- Detekt: `detektAll` - Run the static Kotlin code analysis for the whole project at once.
- The Dependency Analysis: `buildHealth` - Provides advice for managing dependencies and other applied plugins

## License ⚖️
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate.svg?type=large)](https://app.fossa.com/projects/git%2Bgithub.com%2FDrjacky%2FMVVMTemplate?ref=badge_large)
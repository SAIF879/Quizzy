# Quizzy - Student Performance Tracker

**Quizzy** is a modern Android application built with Jetpack Compose, designed for parents to monitor their children's academic performance, track quiz results, and stay updated on their learning progress. The app features a clean, intuitive interface that provides at-a-glance summaries and detailed overviews of a student's activities.

## ✨ Features

*   **Secure Sign-In**: Simple and secure login for parents using a unique School ID and Student ID.
*   **Dashboard Overview**: A central hub displaying key performance metrics:
    *   Student's current availability status.
    *   Total quiz attempts.
    *   Overall accuracy percentage.
*   **Today's Summary**: A "Focused" card highlights specific areas where the student is struggling and provides direct links to relevant learning materials (e.g., video lessons).
*   **Weekly Performance Insights**:
    *   **Quiz Streak**: A visual tracker of daily quiz completion for the week.
    *   **Accuracy Bar**: A progress bar showing the student's overall correctness.
    *   **Performance by Topic**: A breakdown of performance in different subjects, indicating topics that are improving or declining.
*   **Notifications & Settings**:
    *   A dedicated screen for important updates like missed quizzes, earned badges, and teacher notes.
    *   App settings to switch between child profiles, change the language, and log out.
*   **Modern, Dynamic UI**: Built entirely with Jetpack Compose, featuring custom-drawn components and a sleek, responsive design.

## 🛠️ Tech Stack & Architecture

This project is built using 100% Kotlin and leverages the latest Android development technologies.

*   **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose) for declarative UI development, with Material 3 for design components.
*   **Architecture**: Follows the **MVVM (Model-View-ViewModel)** architecture pattern to ensure a clean separation of concerns.
*   **Dependency Injection**: [Koin](https://insert-koin.io/) is used for managing dependencies, making the codebase more modular and testable.
*   **Networking**: [Retrofit](https://square.github.io/retrofit/) and [OkHttp](https://square.github.io/okhttp/) for making API calls to a backend service, with [KotlinX Serialization](https://github.com/Kotlin/kotlinx.serialization) for parsing JSON data.
*   **Asynchronous Programming**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) are used throughout the app for managing background threads and handling asynchronous operations smoothly.
*   **Navigation**: [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) for handling screen transitions within the app.
*   **Authentication**: [Firebase Authentication](https://firebase.google.com/docs/auth) for managing user sign-in securely.

## 🚀 Getting Started

To get the project up and running, follow these steps:

### **1. Prerequisites**

*   Android Studio Iguana | 2023.2.1 or newer.
*   Kotlin plugin installed in Android Studio.
*   A Firebase project set up.
*   clone this repo
*   run in android studio
    

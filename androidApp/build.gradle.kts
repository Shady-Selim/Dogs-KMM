plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.shady.dogskmm.android"
        minSdk = 25
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.5"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    // Integration with activities
    implementation ("androidx.activity:activity-compose:1.4.0")
    // Compose Material Design
    implementation ("androidx.compose.material:material:1.0.5")
    // Animations
    implementation ("androidx.compose.animation:animation:1.0.5")
    // Tooling support (Previews, etc.)
    implementation ("androidx.compose.ui:ui:1.0.5")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.0.5")
    // Integration with ViewModels
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    // Livedata
    implementation ("androidx.compose.runtime:runtime-livedata:1.0.5")
    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    // Coil Library for image loading
    implementation("io.coil-kt:coil-compose:1.3.2")
    // Coroutines Library
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
}
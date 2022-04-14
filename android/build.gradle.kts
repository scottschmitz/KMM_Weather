plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "com.sschmitz.kmm_weather.android"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":app"))
    implementation("org.kodein.di:kodein-di-framework-android-x:7.11.0")

    implementation ("androidx.activity:activity-compose:1.3.1")
    implementation("androidx.compose.ui:ui:1.0.0")
    implementation("androidx.compose.material:material:1.0.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")



//    implementation("androidx.compose.ui:ui:1.1.1")
//    // Tooling support (Previews, etc.)
//    implementation("androidx.compose.ui:ui-tooling:1.1.1")
//    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
//    implementation("androidx.compose.foundation:foundation:1.1.1")
//    // Material Design
//    implementation("androidx.compose.material:material:1.1.1")
//    // Material design icons
//    implementation("androidx.compose.material:material-icons-core:1.1.1")
//    implementation("androidx.compose.material:material-icons-extended:1.1.1")
//    // Integration with activities
//    implementation("androidx.activity:activity-compose:1.4.0")
//    // Integration with ViewModels
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
//    // Integration with observables
    implementation("androidx.compose.runtime:runtime-livedata:1.1.1")

    implementation("androidx.navigation:navigation-compose:2.4.2")
}

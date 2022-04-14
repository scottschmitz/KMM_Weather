pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

rootProject.name = "KMM_Weather"
include(":android")
include(":app")
include(":domain")
include(":data")
include(":presentation")

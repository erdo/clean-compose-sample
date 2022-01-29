plugins {
    id("android-module-plugin")
}

android {
    lint {
        isAbortOnError = true
        lintConfig = File(project.rootDir, "app/lint-app.xml")
    }
}

dependencies {
    implementation(project(":app:domain"))
}

apply(from = "../dependencies-android.gradle.kts")


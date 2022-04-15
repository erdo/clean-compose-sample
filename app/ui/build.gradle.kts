plugins {
    id("android-module-plugin")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = foo.bar.clean.Shared.Versions.compose
    }
    android {
        lint {
            abortOnError = true
            lintConfig = File(project.rootDir, "app/lint-app.xml")
        }
    }
}

dependencies {
    implementation(project(":app:domain"))
}

apply(from = "../dependencies-android.gradle.kts")

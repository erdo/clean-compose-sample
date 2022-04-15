plugins {
    id("android-app-plugin")
    id("kotlin-kapt")
}

android {

    defaultConfig {
        versionCode = 1
        versionName = "0.1"
    }

    lint {
        abortOnError = true
        lintConfig = File(project.rootDir, "app/lint-app.xml")
    }
}

dependencies {

    implementation(project("data"))
    implementation(project("domain"))
    implementation(project("ui"))

    // only test dependencies listed here

    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.11.0")

    androidTestImplementation("io.mockk:mockk-android:1.11.0")
    androidTestImplementation("androidx.test:core:1.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.3")
    androidTestImplementation("androidx.annotation:annotation:1.3.0")
}

apply(from = "dependencies-android.gradle.kts")

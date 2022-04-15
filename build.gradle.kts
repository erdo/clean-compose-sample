/**
 * ./gradlew check
 * ./gradlew testDebugUnitTest
 * ./gradlew connectedAndroidTest -PtestBuildType=debug --no-daemon --no-parallel
 * ./gradlew connectedAndroidTest -PtestBuildType=release
 * ./gradlew example-kt-01reactiveui:testDebugUnitTest --info
 * ./gradlew example-kt-04retrofit:dependencies --configuration releaseRuntimeClasspath
 */
buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${foo.bar.clean.Shared.Versions.kotlin}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${foo.bar.clean.Shared.Versions.kotlin}")
        classpath("com.android.tools.build:gradle:7.1.3")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}

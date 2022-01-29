package foo.bar.clean

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import java.io.File

class AndroidModulePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.applyLibraryPlugins()
        project.configureAndroidModule()
    }
}

class AndroidAppPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.applyAppPlugins()
        project.configureAndroidApp(project.file("../keystore/debug.fake_keystore"))
    }
}

internal fun Project.configureAndroidApp(keyStoreFile: File) = this.extensions.getByType<AppExtension>().run {

    (this as BaseExtension).configure()

    // android {
    signingConfigs {
        create("release") {
            // keytool -genkey -v -keystore debug.fake_keystore -storetype PKCS12 -alias android -storepass android -keypass android -keyalg RSA -keysize 2048 -validity 20000 -dname "cn=Unknown, ou=Unknown, o=Unknown, c=Unknown"
            storeFile = keyStoreFile
            storePassword = "android"
            keyAlias = "android"
            keyPassword = "android"
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-app.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }
    // }
}

internal fun Project.configureAndroidModule() = this.extensions.getByType<LibraryExtension>().run {

    (this as BaseExtension).configure()

    // android {
    //  ...
    // }
}

internal fun BaseExtension.configure() = this.run {

    // android {
    compileSdkVersion(Shared.Android.compileSdk)
    compileOptions {
        sourceCompatibility = Shared.Android.javaVersion
        targetCompatibility = Shared.Android.javaVersion
    }
    defaultConfig {

        minSdk = Shared.Android.minSdk
        targetSdk = Shared.Android.targetSdk

        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    // }
}

internal fun Project.applyLibraryPlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("org.jetbrains.kotlin.plugin.serialization")
    plugins.apply("com.android.library")
}

internal fun Project.applyAppPlugins() {
    plugins.apply("kotlin-android")
    plugins.apply("org.jetbrains.kotlin.plugin.serialization")
    plugins.apply("com.android.application")
}

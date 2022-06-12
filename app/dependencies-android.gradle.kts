dependencies {
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${foo.bar.clean.Shared.Versions.kotlin}")
    implementation("androidx.multidex:multidex:2.0.1")
    // image loading
    implementation("io.coil-kt:coil:1.2.1")
    // reactivity
    implementation("co.early.fore:fore-kt-android:${foo.bar.clean.Shared.Versions.fore}")
    implementation("co.early.fore:fore-kt-android-compose:${foo.bar.clean.Shared.Versions.compose}")
    // network
    implementation("io.ktor:ktor-client-serialization:1.5.2")
    implementation("io.ktor:ktor-client-okhttp:1.5.2")
    // persistence
    implementation("co.early.persista:persista:${foo.bar.clean.Shared.Versions.persista}")
    // di
    implementation("io.insert-koin:koin-androidx-compose:3.1.5")
    // compose
    implementation("androidx.compose.ui:ui:${foo.bar.clean.Shared.Versions.compose}")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.compose.material:material:${foo.bar.clean.Shared.Versions.compose}")
    implementation("androidx.compose.ui:ui-tooling:${foo.bar.clean.Shared.Versions.compose}")
    debugImplementation("androidx.compose.ui:ui-tooling-preview:${foo.bar.clean.Shared.Versions.compose}")
}

fun Project.dependencies(configuration: DependencyHandlerScope.() -> Unit) =
    DependencyHandlerScope.of(dependencies).configuration()

fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.`debugImplementation`(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

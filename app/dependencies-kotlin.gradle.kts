dependencies {
    // persistence
    implementation("co.early.persista:persista:${foo.bar.clean.Shared.Versions.persista}")
    // reactivity
    implementation("co.early.fore:fore-kt-core:${foo.bar.clean.Shared.Versions.fore}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${foo.bar.clean.Shared.Versions.kotlin}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
}

fun Project.dependencies(configuration: DependencyHandlerScope.() -> Unit) =
    DependencyHandlerScope.of(dependencies).configuration()

fun DependencyHandler.`implementation`(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)
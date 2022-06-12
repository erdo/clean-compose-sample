plugins {
  `kotlin-dsl`
}

repositories {
  gradlePluginPortal()
  mavenCentral()
  google()
}

gradlePlugin {
  plugins {
    register("android-app-plugin") {
      id = "android-app-plugin"
      implementationClass = "foo.bar.clean.AndroidAppPlugin"
    }
    register("android-module-plugin") {
      id = "android-module-plugin"
      implementationClass = "foo.bar.clean.AndroidModulePlugin"
    }
  }
}

dependencies {

  // we want the kotlin and android gradle plugin, because we want to access them in our plugin
  implementation("com.android.tools.build:gradle:7.2.1")
  //implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${foo.bar.clean.Shared.Versions.kotlin}")
  implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")

  //for custom plugins
  implementation(gradleApi())
  implementation(localGroovy())
}

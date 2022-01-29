package foo.bar.clean

import org.gradle.api.JavaVersion
import java.io.File
import java.util.*

object Shared {

    object Android {
        const val minSdk = 21
        const val compileSdk = 31
        const val targetSdk = 31
        val javaVersion = JavaVersion.VERSION_1_8
    }

    object Versions {
        const val kotlin = "1.6.10"
        const val compose = "1.1.0-rc03"
        const val fore = "1.5.8"
        const val persista = "1.0.0"
    }

    object Secrets {

        private val secrets = readProperties(File("../secrets/secrets.properties"))

        val SOME_USER = (System.getenv("SOME_USER") ?: secrets.getProperty("SOME_USER")) ?: "MISSING"
        val SOME_PASSWORD = (System.getenv("SOME_PASSWORD") ?: secrets.getProperty("SOME_PASSWORD")) ?: "MISSING"
    }
}

fun readProperties(propertiesFile: File): Properties {
    return Properties().apply {
        try {
            propertiesFile.inputStream().use { fis ->
                load(fis)
            }
            println("[SECRETS LOADED]\n")
        } catch (exception: Exception) {
            println("WARNING $propertiesFile not found! \n")
            println("exception: $exception \n")
        }
    }
}

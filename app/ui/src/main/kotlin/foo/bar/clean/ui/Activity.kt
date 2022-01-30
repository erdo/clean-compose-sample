package foo.bar.clean.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import foo.bar.clean.ui.common.AppTheme
import foo.bar.clean.ui.home.HomeScreen
import foo.bar.clean.ui.splash.SplashScreen

class Activity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            AppTheme {

                var showSplash by rememberSaveable { mutableStateOf(true) }

                if (showSplash) {
                    SplashScreen { showSplash = false }
                } else {
                    HomeScreen()
                }
            }
        }
    }
}

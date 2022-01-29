package foo.bar.clean.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import foo.bar.clean.ui.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {

    val currentOnTimeout by rememberUpdatedState(onTimeout)

    LaunchedEffect(true) { // Effect matches the lifecycle of the SplashScreen
        delay(1000)
        currentOnTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = R.dimen.common_space_large))
                .background(
                    color = MaterialTheme.colors.primaryVariant,
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.common_space_large))
                )
                .align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center),
                text = "${stringResource(id = R.string.app_name)} Splash"
            )
        }
    }
}

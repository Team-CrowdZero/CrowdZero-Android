package com.gdg.feature.splash

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import kotlinx.coroutines.delay

@Composable
fun SplashRoute(
    splashViewModel: SplashViewModel = hiltViewModel(),
    navigateToMap: () -> Unit
) {
    BackHandler(enabled = true) {}

    LaunchedEffect(key1 = splashViewModel.sideEffects) {
        splashViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is SplashSideEffect.NavigateToMap -> navigateToMap()
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(2000)
        navigateToMap()
    }

    SplashScreen()
}

@Composable
fun SplashScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Splash Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    CrowdZeroAndroidTheme {
        SplashScreen()
    }
}

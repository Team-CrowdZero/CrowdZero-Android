package com.gdg.feature.splash

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme

@Composable
fun SplashRoute(
    navigateToMap: () -> Unit
) {
    SplashScreen()
}

@Composable
fun SplashScreen() {

}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    CrowdZeroAndroidTheme {
        SplashScreen()
    }
}

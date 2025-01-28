package com.gdg.crowdzero_android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gdg.core.navigation.Route
import com.gdg.feature.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavGraphBuilder.splashNavGraph(
    navHostController: NavController
) {
    composable<Splash> {
        SplashRoute(
            navigateToMap = {
                navHostController.navigateMap()
            }
        )
    }
}

@Serializable
data object Splash : Route

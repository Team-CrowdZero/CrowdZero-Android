package com.gdg.crowdzero_android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdg.core.navigation.MainTabRoute
import com.gdg.feature.map.MapRoute
import kotlinx.serialization.Serializable

fun NavController.navigateMap(navOptions: NavOptions? = null) {
    navigate(
        route = Map,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.mapNavGraph(
    navHostController: NavController
) {
    composable<Map> {
        MapRoute(
            navigateToDetail = { id ->
                navHostController.navigateDetail(id = id)
            }
        )
    }
}

@Serializable
data object Map : MainTabRoute

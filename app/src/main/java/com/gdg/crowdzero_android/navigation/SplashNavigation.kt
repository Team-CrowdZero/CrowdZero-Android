package com.gdg.crowdzero_android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gdg.core.navigation.Route
import com.gdg.feature.detail.DetailRoute
import kotlinx.serialization.Serializable

fun NavController.navigateSplash(
    id: Int, navOptions: NavOptions? = null
) {
    navigate(
        route = Splash(id = id), navOptions = navOptions
    )
}

fun NavGraphBuilder.splashNavGraph(
    paddingValues: PaddingValues
) {
    composable<Detail> {
        val args = it.toRoute<Detail>()
        DetailRoute(
            id = args.id, paddingValues = paddingValues
        )
    }
}

@Serializable
data class Splash(
    val id: Int
) : Route

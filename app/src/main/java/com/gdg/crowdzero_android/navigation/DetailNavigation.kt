package com.gdg.crowdzero_android.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.gdg.core.navigation.Route
import com.gdg.feature.detail.DetailRoute
import kotlinx.serialization.Serializable

fun NavController.navigateDetail(
    id: Int,
    navOptions: NavOptions? = null
) {
    navigate(
        route = Detail(id = id),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.detailNavGraph(
    navHostController: NavController
) {
    composable<Detail> {
        val args = it.toRoute<Detail>()
        DetailRoute(
            id = args.id,
            navigateUp = navHostController::navigateUp
        )
    }
}

@Serializable
data class Detail(
    val id: Int
) : Route

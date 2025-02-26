package com.gdg.crowdzero_android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.gdg.core.navigation.MainTabRoute
import com.gdg.feature.calendar.CalendarRoute
import kotlinx.serialization.Serializable

fun NavController.navigateCalendar(navOptions: NavOptions? = null) {
    navigate(
        route = Calendar,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.calendarNavGraph(
    paddingValues: PaddingValues
) {
    composable<Calendar> {
        CalendarRoute(
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object Calendar : MainTabRoute

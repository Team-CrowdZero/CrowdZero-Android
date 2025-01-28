package com.gdg.crowdzero_android.main

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.gdg.core.navigation.MainTabRoute
import com.gdg.core.navigation.Route
import com.gdg.crowdzero_android.R
import com.gdg.crowdzero_android.navigation.Calendar
import com.gdg.crowdzero_android.navigation.Map

enum class MainTab(
    val index: Int,
    @StringRes val contentDescription: Int,
    val route: MainTabRoute
) {
    MAP(
        index = 0,
        contentDescription = R.string.main_tab_map,
        route = Map
    ),
    CALENDAR(
        index = 1,
        contentDescription = R.string.main_tab_calendar,
        route = Calendar
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}
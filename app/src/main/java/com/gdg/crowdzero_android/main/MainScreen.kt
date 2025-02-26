package com.gdg.crowdzero_android.main

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.gdg.core.designsystem.component.snackbar.BaseSnackBar
import com.gdg.core.designsystem.component.topappbar.BaseTopAppBar
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.designsystem.theme.Green600
import com.gdg.core.util.NoRippleInteractionSource
import com.gdg.crowdzero_android.R
import com.gdg.crowdzero_android.navigation.calendarNavGraph
import com.gdg.crowdzero_android.navigation.detailNavGraph
import com.gdg.crowdzero_android.navigation.mapNavGraph
import com.gdg.feature.splash.SplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    var showSplash by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val systemUiController = rememberSystemUiController()
    val lifecycleOwner = LocalLifecycleOwner.current
    var backPressedState by remember { mutableStateOf(true) }
    var backPressedTime = 0L
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    SideEffect {
        systemUiController.setStatusBarColor(
            color = if (showSplash) Green600 else White
        )
    }
    DisposableEffect(key1 = lifecycleOwner) {
        onDispose {
            systemUiController.setStatusBarColor(
                color = Color.Transparent
            )
        }
    }

    LaunchedEffect(Unit) {
        delay(2000)
        showSplash = false
    }

    if (showSplash) {
        SplashScreen()
    } else {
        BackHandler(enabled = backPressedState) {
            if (System.currentTimeMillis() - backPressedTime <= 3000) {
                (context as Activity).finish()
            } else {
                backPressedState = true
                coroutineScope.launch {
                    val job =
                        launch { snackBarHostState.showSnackbar(message = context.getString(R.string.snack_bar_back_message)) }
                    delay(2000)
                    job.cancel()
                }
            }
            backPressedTime = System.currentTimeMillis()
        }

        Scaffold(modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
            snackbarHost = {
                SnackbarHost(
                    hostState = snackBarHostState, modifier = Modifier.padding(bottom = 10.dp)
                ) { snackBarData ->
                    BaseSnackBar(
                        message = snackBarData.visuals.message
                    )
                }
            },
            topBar = {
                Column {
                    val navBackStackEntry by navigator.navController.currentBackStackEntryAsState()
                    val currentRoute =
                        navBackStackEntry?.destination?.route?.substringAfterLast(".")
                            ?.substringBefore("/")

                    BaseTopAppBar(
                        route = currentRoute,
                        isIconVisible = currentRoute == "Detail",
                        onBackButtonClick = navigator::navigateUp
                    )
                    MainTabBar(
                        isVisible = navigator.showTabRow(),
                        tabs = MainTab.entries.toList(),
                        currentTab = navigator.currentTab,
                        onTabSelected = navigator::navigate
                    )
                }
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
            ) {
                NavHost(
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    popExitTransition = { ExitTransition.None },
                    navController = navigator.navController,
                    startDestination = navigator.startDestination
                ) {
                    mapNavGraph(navHostController = navigator.navController)
                    calendarNavGraph(paddingValues = paddingValues)
                    detailNavGraph(paddingValues = paddingValues)
                }
            }
        }
    }
}


@Composable
fun MainTabBar(
    isVisible: Boolean, tabs: List<MainTab>, currentTab: MainTab?, onTabSelected: (MainTab) -> Unit
) {
    val pagerState = rememberPagerState(initialPage = currentTab?.index ?: 0) { tabs.size }
    val coroutineScope = rememberCoroutineScope()

    AnimatedVisibility(
        visible = isVisible, enter = EnterTransition.None, exit = ExitTransition.None
    ) {
        TabRow(selectedTabIndex = pagerState.currentPage,
            containerColor = CrowdZeroTheme.colors.white,
            contentColor = CrowdZeroTheme.colors.green700,
            indicator = { tabPositions ->
                SecondaryIndicator(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[currentTab?.index ?: 0])
                        .clip(CircleShape), height = 4.dp, color = CrowdZeroTheme.colors.green700
                )
            },
            divider = {}) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = currentTab == tab,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                        onTabSelected(tab)
                    },
                    modifier = Modifier.padding(vertical = 8.5.dp),
                    selectedContentColor = CrowdZeroTheme.colors.green700,
                    unselectedContentColor = CrowdZeroTheme.colors.gray600,
                    interactionSource = NoRippleInteractionSource
                ) {
                    Text(
                        text = stringResource(tab.contentDescription),
                        style = CrowdZeroTheme.typography.c2JalnanGothic
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainTabBarPreview() {
    CrowdZeroAndroidTheme {
        MainTabBar(
            isVisible = true,
            tabs = MainTab.entries,
            currentTab = MainTab.MAP,
            onTabSelected = {})
    }
}

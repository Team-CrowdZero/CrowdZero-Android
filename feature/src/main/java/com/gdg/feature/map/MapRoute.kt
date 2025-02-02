package com.gdg.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.NaverMapConstants
import com.naver.maps.map.compose.rememberCameraPositionState

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.feature.R

@Composable
fun MapRoute(
    mapViewModel: MapViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 100.0,
                minZoom = 5.0,
                locationTrackingMode = LocationTrackingMode.Follow,
            )
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(isLocationButtonEnabled = true)
        )
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(NaverMapConstants.DefaultCameraPosition.target, 10.0)
    }

    LaunchedEffect(key1 = mapViewModel.sideEffects) {
        mapViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is MapSideEffect.NavigateToDetail -> navigateToDetail(sideEffect.id)
            }
        }
    }

    MapScreen(
        mapProperties = mapProperties,
        mapUiSettings = mapUiSettings,
        cameraPositionState = cameraPositionState,
        onButtonClick = mapViewModel::navigateToDetail
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(
    mapProperties: MapProperties = MapProperties(),
    mapUiSettings: MapUiSettings = MapUiSettings(),
    cameraPositionState: CameraPositionState = CameraPositionState(),
    onButtonClick: (Int) -> Unit = { },
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings
        )
        Button(
            onClick = { onButtonClick(1) },
            modifier = Modifier
                .align(Alignment.BottomEnd)
        ) {
            Text(text = "상세 페이지로 이동")
        }
    }
}


@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(CrowdZeroTheme.colors.green600, CrowdZeroTheme.colors.green700)
                )
            )
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.splash_title1),
                style = CrowdZeroTheme.typography.h1JalnanGothic,
                color = CrowdZeroTheme.colors.white,
                modifier = Modifier.graphicsLayer(rotationZ = -2f)
                    .offset(y = -20.dp)
            )

            Text(
                text = stringResource(R.string.splash_title2),
                style = CrowdZeroTheme.typography.h1JalnanGothic,
                color = CrowdZeroTheme.colors.white,
                modifier = Modifier.graphicsLayer(rotationZ = -2f)
                    .offset(x = 2.dp, y=-27.dp)
            )

            Text(
                text = stringResource(R.string.splash_subtitle),
                style = CrowdZeroTheme.typography.c4Regular,
                color = CrowdZeroTheme.colors.white,
                modifier = Modifier.offset(x=20.dp, y=-35.dp)

            )


        }


        Image(
            painter = painterResource(id = R.drawable.ic_buildings),
            contentDescription = stringResource(id = R.string.splash_desc),
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .align(Alignment.BottomCenter)
                . offset(y = 17.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    CrowdZeroAndroidTheme {
        MapScreen()
    }
}

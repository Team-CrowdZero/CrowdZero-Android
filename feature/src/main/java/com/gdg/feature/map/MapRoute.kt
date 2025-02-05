package com.gdg.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.component.chip.MapChip
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.extension.noRippleClickable
import com.gdg.core.type.LocationType
import com.gdg.domain.entity.PlaceEntity
import com.gdg.feature.R
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.overlay.OverlayImage

@Composable
fun MapRoute(
    mapViewModel: MapViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
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

    val locations = listOf(
        LocationType.GANGNAM_STATION,
        LocationType.GWANGHWAMUN,
        LocationType.SAMGAKJI_STATION,
        LocationType.SEOUL_STATION,
        LocationType.YEOUIDO
    )

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
        onButtonClick = mapViewModel::navigateToDetail,
        locations = locations
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun MapScreen(
    paddingValues: PaddingValues = PaddingValues(),
    mapProperties: MapProperties = MapProperties(),
    mapUiSettings: MapUiSettings = MapUiSettings(),
    cameraPositionState: CameraPositionState = CameraPositionState(),
    onButtonClick: (Int) -> Unit = { },
    mapViewModel: MapViewModel = hiltViewModel(),
    locations: List<LocationType>
) {
    val selectedLocation = remember { mutableStateOf<LocationType?>(null) }
    val selectedPlace by mapViewModel.selectedPlace.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings
        ) {

            selectedLocation.value?.let { location ->
                Marker(
                    state = MarkerState(position = location.latLng),
                    icon = OverlayImage.fromResource(R.drawable.ic_map_marker),
                    captionText = stringResource(id = location.title),
                    captionColor = CrowdZeroTheme.colors.gray900,
                    captionHaloColor = CrowdZeroTheme.colors.white
                )

            }
        }

        LazyRow(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 110.dp, start = 10.dp)
        ) {
            items(locations) { location ->
                MapChip(
                    title = location,
                    isSelected = selectedLocation.value == location,
                    onClick = {
                        selectedLocation.value = location
                        cameraPositionState.move(
                            CameraUpdate.scrollAndZoomTo(location.latLng, 10.0)
                                .animate(CameraAnimation.Easing)
                        )
                        mapViewModel.selectPlace(location.id)
                    },

                )
            }
        }

        selectedPlace?.let {
            PlaceInfoCard(
                place = it,
                modifier = Modifier.align(Alignment.BottomCenter),
                onButtonClick = onButtonClick
            )
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

@Composable
fun PlaceInfoCard(place: PlaceEntity?, modifier: Modifier = Modifier,onButtonClick: (Int) -> Unit) {
    if (place == null) return

    Box(
        modifier = modifier
            .width(500.dp)
            .height(170.dp)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .graphicsLayer {
                shadowElevation = 20f
                shape = RoundedCornerShape(20.dp)
                clip = false
            }
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color.Black.copy(alpha = 1f),
                spotColor = Color.Black.copy(alpha = 4f)
            )
            .background(CrowdZeroTheme.colors.white, shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.TopStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_nextbuildings),
            contentDescription = "배경 빌딩 아이콘",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.place_info_detail_view),
                    modifier = Modifier
                        .size(35.dp)
                        .padding(start = 15.dp, top = 15.dp)
                        .noRippleClickable { onButtonClick(1) },
                )
            }


            Text(
                text = place.name,
                style = CrowdZeroTheme.typography.h3JalnanGothic,
                color = CrowdZeroTheme.colors.gray900,
                modifier = Modifier
                    .offset(y = -7.dp)
            )

            Spacer(modifier = Modifier.height(15.dp))


            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.place_info_congestion))
                    append(" ")
                    withStyle(
                        style = SpanStyle(
                            color = when (place.congestion) {
                                "여유" -> CrowdZeroTheme.colors.green600
                                "보통" -> CrowdZeroTheme.colors.yellow
                                "약간 혼잡" -> CrowdZeroTheme.colors.orange
                                else -> CrowdZeroTheme.colors.red
                            }
                        )
                    ) {
                        append(place.congestion)
                    }
                },
                style = CrowdZeroTheme.typography.c4SemiBold,
                color = CrowdZeroTheme.colors.gray700
            )


            Spacer(modifier = Modifier.height(3.dp))


            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.place_info_realtime_population))
                    append(" ")
                    withStyle(
                        style = SpanStyle(color = CrowdZeroTheme.colors.green700)
                    ) {
                        append(stringResource(R.string.place_info_realtime_population_count, place.min, place.max))
                    }
                },
                style = CrowdZeroTheme.typography.c4SemiBold,
                color = CrowdZeroTheme.colors.gray700
            )
        }
    }
}






@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    SplashScreen()
}

/*@Preview(showBackground = true)
@Composable
fun MapScreenPreview() {
    CrowdZeroAndroidTheme {
        MapScreen()
    }
}*/
@Preview(showBackground = true)
@Composable
fun TestMapChipPreview() {
    Column {
        MapChip(
            title = LocationType.GANGNAM_STATION,
            isSelected = true,
            onClick = {}
        )
        MapChip(
            title = LocationType.GWANGHWAMUN,
            isSelected = false,
            onClick = {}
        )
    }
}

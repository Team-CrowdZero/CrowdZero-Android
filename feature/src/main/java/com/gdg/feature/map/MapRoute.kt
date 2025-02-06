package com.gdg.feature.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.component.chip.MapChip
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.extension.noRippleClickable
import com.gdg.core.type.LocationType
import com.gdg.domain.entity.PlaceEntity
import com.gdg.feature.R
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.CameraPositionState
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.NaverMapConstants
import com.naver.maps.map.compose.rememberCameraPositionState
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
                        if (selectedLocation.value == location) {
                            selectedLocation.value = null
                        } else {
                            selectedLocation.value = location
                            cameraPositionState.move(
                                CameraUpdate.scrollAndZoomTo(location.latLng, 10.0)
                                    .animate(CameraAnimation.Easing)
                            )
                        }
                    })
            }
        }

        selectedLocation.value?.let { location ->
            val place = mapViewModel.getPlaceInfo(location.id)
            PlaceInfoCard(
                place = place,
                modifier = Modifier.align(Alignment.BottomCenter),
                onButtonClick = onButtonClick
            )
        }
    }
}

@Composable
fun PlaceInfoCard(
    place: PlaceEntity?, modifier: Modifier = Modifier, onButtonClick: (Int) -> Unit
) {
    if (place == null) return

    Box(modifier = modifier
        .noRippleClickable { onButtonClick(place.id) }
        .fillMaxWidth()
        .fillMaxHeight(0.2f)
        .padding(horizontal = 15.dp, vertical = 20.dp)
        .graphicsLayer {
            shadowElevation = 20f
            shape = RoundedCornerShape(9.dp)
            clip = false
        }
        .shadow(
            elevation = 20.dp,
            shape = RoundedCornerShape(9.dp),
            ambientColor = CrowdZeroTheme.colors.shadow.copy(alpha = 1f),
            spotColor = CrowdZeroTheme.colors.shadow.copy(alpha = 4f)
        )
        .background(CrowdZeroTheme.colors.white, shape = RoundedCornerShape(9.dp)),
        contentAlignment = Alignment.TopStart) {
        Image(
            painter = painterResource(id = R.drawable.ic_map_buildings),
            contentDescription = stringResource(R.string.place_info_card_buildings),
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.Crop,
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = place.name,
                    style = CrowdZeroTheme.typography.h3JalnanGothic,
                    color = CrowdZeroTheme.colors.gray900,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 9.dp)
                )

                Spacer(modifier = Modifier.weight(1f)) // Text와 Image 사이 간격 확보

                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.place_info_detail_view),
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 10.dp,end = 3.dp), // 여백 추가 가능
                    colorFilter = ColorFilter.tint(CrowdZeroTheme.colors.gray800)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.place_info_congestion))
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
                color = CrowdZeroTheme.colors.gray800,
                modifier = Modifier
                    .padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.place_info_realtime_population))
                    withStyle(
                        style = SpanStyle(color = CrowdZeroTheme.colors.green700)
                    ) {
                        append(
                            stringResource(
                                R.string.place_info_realtime_population_count, place.min, place.max
                            )
                        )
                    }
                },
                style = CrowdZeroTheme.typography.c4SemiBold,
                color = CrowdZeroTheme.colors.gray700,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }
    }
}
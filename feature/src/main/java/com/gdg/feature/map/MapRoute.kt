package com.gdg.feature.map

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.component.chip.MapChip
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.extension.noRippleClickable
import com.gdg.core.type.LocationType
import com.gdg.domain.entity.PlaceEntity
import com.gdg.domain.entity.RoadEntity
import com.gdg.feature.R
import com.naver.maps.geometry.LatLng
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
import kotlinx.coroutines.launch

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
        position = CameraPosition(NaverMapConstants.DefaultCameraPosition.target, 13.0)
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
        locations = mapViewModel.locations,
        roads = mapViewModel.mockRoadEntity,
        getPlaceEntity = { id -> mapViewModel.getPlaceEntity(id) },
        onButtonClick = mapViewModel::navigateToDetail
    )
}

@OptIn(ExperimentalNaverMapApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    mapProperties: MapProperties = MapProperties(),
    mapUiSettings: MapUiSettings = MapUiSettings(),
    cameraPositionState: CameraPositionState = CameraPositionState(),
    locations: List<LocationType>,
    roads: List<RoadEntity>,
    getPlaceEntity: (Int) -> PlaceEntity?,
    onButtonClick: (Int) -> Unit = { }
) {
    var selectedLocation by remember { mutableStateOf<LocationType?>(null) }
    var selectedRoad by remember { mutableStateOf<RoadEntity?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    BackHandler(
        enabled = selectedLocation != null,
    ) {
        selectedLocation = null
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        NaverMap(
            cameraPositionState = cameraPositionState,
            properties = mapProperties,
            uiSettings = mapUiSettings
        ) {
            selectedLocation?.let { location ->
                Marker(
                    state = MarkerState(position = location.latLng),
                    icon = OverlayImage.fromResource(R.drawable.ic_map_marker),
                    captionText = stringResource(id = location.title),
                    captionColor = CrowdZeroTheme.colors.gray900,
                    captionHaloColor = CrowdZeroTheme.colors.white
                )
            }
            if (roads.isNotEmpty()) {
                roads.forEach { road ->
                    Marker(
                        width = 20.dp,
                        height = 20.dp,
                        state = MarkerState(position = LatLng(road.acdntY, road.acdntX)),
                        icon = OverlayImage.fromResource(R.drawable.ic_ban_marker),
                        onClick = {
                            selectedRoad = road
                            showBottomSheet = true
                            coroutineScope.launch {
                                sheetState.show()
                            }
                            true
                        }
                    )
                }
            }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 110.dp, start = 10.dp, end = 10.dp),
            state = listState
        ) {
            itemsIndexed(locations) { index, location ->
                MapChip(
                    title = location,
                    isSelected = selectedLocation == location,
                    onClick = {
                        if (selectedLocation == location) {
                            selectedLocation = null
                        } else {
                            selectedLocation = location
                            cameraPositionState.move(
                                CameraUpdate.scrollAndZoomTo(location.latLng, 17.0)
                                    .animate(CameraAnimation.Easing)
                            )
                        }
                        coroutineScope.launch {
                            listState.animateScrollToItem(index)
                        }
                    }
                )
            }
        }
        selectedLocation?.let { location ->
            PlaceInfoCard(
                place = getPlaceEntity(location.id),
                modifier = Modifier.align(Alignment.BottomCenter),
                onButtonClick = onButtonClick
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false },
            containerColor = CrowdZeroTheme.colors.white,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            RoadInfoSheet(road = selectedRoad)
        }
    }
}

@Composable
fun PlaceInfoCard(
    place: PlaceEntity?,
    modifier: Modifier = Modifier,
    onButtonClick: (Int) -> Unit
) {
    if (place == null) return

    Box(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 21.dp)
            .noRippleClickable { onButtonClick(place.id) }
            .fillMaxWidth()
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
            .background(CrowdZeroTheme.colors.white, shape = RoundedCornerShape(9.dp))
            .zIndex(1f)
    ) {
        Image(
            modifier = Modifier
                .matchParentSize()
                .align(Alignment.CenterEnd)
                .offset(x = (LocalConfiguration.current.screenWidthDp.dp * 0.14f)),
            painter = painterResource(id = R.drawable.ic_map_buildings),
            contentDescription = stringResource(R.string.place_info_card_buildings),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 21.dp, start = 19.dp, bottom = 21.dp, end = 6.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 13.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = place.name,
                    style = CrowdZeroTheme.typography.h3JalnanGothic,
                    color = CrowdZeroTheme.colors.gray900,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow),
                    contentDescription = stringResource(R.string.place_info_detail_view),
                    tint = CrowdZeroTheme.colors.gray800
                )
            }
            Text(
                modifier = Modifier.padding(bottom = 3.dp),
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
                color = CrowdZeroTheme.colors.gray800
            )
            Text(
                text = buildAnnotatedString {
                    append(stringResource(R.string.place_info_realtime_population))
                    withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.green700)) {
                        append(
                            stringResource(
                                R.string.place_info_realtime_population_count,
                                place.min,
                                place.max
                            )
                        )
                    }
                },
                style = CrowdZeroTheme.typography.c4SemiBold,
                color = CrowdZeroTheme.colors.gray800
            )
        }
    }
}

@Composable
fun RoadInfoSheet(road: RoadEntity?) {
    if (road == null) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 21.dp, end = 16.dp, top = 14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.5.dp,
                    color = CrowdZeroTheme.colors.gray500,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 13.dp, vertical = 12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = stringResource(R.string.map_modal_header),
                    color = CrowdZeroTheme.colors.gray900,
                    style = CrowdZeroTheme.typography.h5Bold
                )
                Text(
                    text = stringResource(R.string.map_modal_generated_time, road.acdntOccrDt),
                    color = CrowdZeroTheme.colors.gray700,
                    style = CrowdZeroTheme.typography.c3Medium
                )
                Text(
                    text = stringResource(R.string.map_modal_complete_time, road.expClrDt),
                    color = CrowdZeroTheme.colors.gray700,
                    style = CrowdZeroTheme.typography.c3Medium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.size(35.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_ban_marker),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, bottom = 3.dp),
                text = stringResource(R.string.map_modal_description, road.acdntInfo),
                color = CrowdZeroTheme.colors.gray800,
                style = CrowdZeroTheme.typography.c3Medium,
                textAlign = TextAlign.Start,
                lineHeight = 13.sp
            )
            Text(
                text = stringResource(R.string.map_modal_update_time, road.acdntTime),
                color = CrowdZeroTheme.colors.gray700,
                style = CrowdZeroTheme.typography.c4Regular
            )
            Text(
                text = stringResource(R.string.map_modal_source),
                color = CrowdZeroTheme.colors.gray700,
                style = CrowdZeroTheme.typography.c4Regular,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceInfoCardPreview() {
    CrowdZeroAndroidTheme {
        PlaceInfoCard(
            place = PlaceEntity(
                id = 1,
                name = "강남역",
                congestion = "보통",
                min = 100,
                max = 200
            ),
            onButtonClick = { }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RoadInfoSheetPreview() {
    CrowdZeroAndroidTheme {
        RoadInfoSheet(
            road = RoadEntity(
                areaId = 1,
                acdntY = 37.123456,
                acdntX = 127.123456,
                acdntInfo = "세종대로 (교보빌딩 → 광화문역2번출구) 집회 무대설치로 진행방향 전차로 통제(반대가변운영)",
                acdntOccrDt = "2021-09-01 12:00",
                expClrDt = "2021-09-01 13:00",
                acdntTime = "2021-09-01 12:00"
            )
        )
    }
}

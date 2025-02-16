package com.gdg.feature.map

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdg.core.designsystem.component.chip.MapChip
import com.gdg.core.designsystem.component.indicator.LoadingIndicator
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.extension.toast
import com.gdg.core.state.UiState
import com.gdg.core.type.LocationType
import com.gdg.domain.entity.PlaceEntity
import com.gdg.domain.entity.RoadEntity
import com.gdg.feature.R
import com.gdg.feature.map.component.PlaceInfoCard
import com.gdg.feature.map.component.RoadInfoSheet
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
import timber.log.Timber

@Composable
fun MapRoute(
    mapViewModel: MapViewModel = hiltViewModel(),
    navigateToDetail: (Long) -> Unit
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
    val getCongestionState by mapViewModel.getCongestionState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val roads by mapViewModel.roads.collectAsStateWithLifecycle()  // roads 리스트를 가져옵니다.

    LaunchedEffect(Unit) {
        mapViewModel.getRoads()  // 1부터 5까지 도로 정보 가져오기
    }

    LaunchedEffect(key1 = mapViewModel.sideEffects) {
        mapViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is MapSideEffect.NavigateToDetail -> navigateToDetail(sideEffect.id)
                is MapSideEffect.ShowToast -> context.toast(sideEffect.message)
            }
        }
    }

    MapScreen(
        mapProperties = mapProperties,
        mapUiSettings = mapUiSettings,
        cameraPositionState = cameraPositionState,
        locations = mapViewModel.locations,
        roads = roads,
        congestionState = getCongestionState,
        getPlaceEntity = { id -> mapViewModel.getCongestion(id.toInt()) },
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
    congestionState: UiState<PlaceEntity> = UiState.Empty,
    getPlaceEntity: (Long) -> Unit,
    onButtonClick: (Long) -> Unit = { }
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
                            getPlaceEntity(location.id)
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
            when (congestionState) {
                is UiState.Empty -> Timber.e("인구 혼잡도 정보 없음")
                is UiState.Loading -> LoadingIndicator(modifier = Modifier.align(Alignment.BottomCenter))
                is UiState.Success -> {
                    if (congestionState.data.id == location.id) {
                        PlaceInfoCard(
                            place = congestionState.data,
                            modifier = Modifier.align(Alignment.BottomCenter),
                            onButtonClick = onButtonClick
                        )
                    }
                }

                is UiState.Failure -> {
                    Timber.e("인구 혼잡도 정보 실패")
                    PlaceInfoCard(
                        place = PlaceEntity(
                            id = location.id,
                            name = stringResource(id = location.title),
                            congestion = "모름",
                            min = 0,
                            max = 0
                        ),
                        modifier = Modifier.align(Alignment.BottomCenter),
                        onButtonClick = onButtonClick
                    )
                }
            }
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

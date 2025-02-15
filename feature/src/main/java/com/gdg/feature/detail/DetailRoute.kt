package com.gdg.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdg.core.designsystem.component.indicator.LoadingIndicator
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.state.UiState
import com.gdg.core.type.CongestionType
import com.gdg.core.type.LocationType
import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.WeatherEntity
import com.gdg.feature.R
import com.gdg.feature.detail.component.CongestionItem
import com.gdg.feature.detail.component.WeatherItem
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
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
import timber.log.Timber
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun DetailRoute(
    detailViewModel: DetailViewModel = hiltViewModel(),
    id: Long,
    paddingValues: PaddingValues
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
        position = CameraPosition(LocationType.extractLatLng(id), 15.0)
    }
    val time = detailViewModel.getCurrentTimeISO8601()
    val getWeatherState by detailViewModel.getWeatherState.collectAsStateWithLifecycle()
    val getCongestionState by detailViewModel.getCongestionState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        detailViewModel.getWeather(id)
        detailViewModel.getCongestion(id)
    }

    DetailScreen(
        id = id,
        paddingValues = paddingValues,
        weatherState = getWeatherState,
        congestionState = getCongestionState,
        mapProperties = mapProperties,
        mapUiSettings = mapUiSettings,
        cameraPositionState = cameraPositionState,
        title = stringResource(LocationType.extractTitleResource(id)),
        location = LocationType.extractLatLng(id),
        time = time
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun DetailScreen(
    id: Long = 0,
    paddingValues: PaddingValues = PaddingValues(),
    weatherState: UiState<WeatherEntity> = UiState.Empty,
    congestionState: UiState<CongestionEntity> = UiState.Empty,
    mapProperties: MapProperties = MapProperties(),
    mapUiSettings: MapUiSettings = MapUiSettings(),
    cameraPositionState: CameraPositionState = CameraPositionState(),
    title: String = "",
    location: LatLng = LatLng(37.574187, 126.976882),
    time: String = ""
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(scrollState)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(bottom = dimensionResource(R.dimen.default_padding))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 3.dp, bottom = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                            append(stringResource(R.string.detail_header_now))
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.green700)) {
                            append(title)
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                            append(stringResource(R.string.detail_header_adverb))
                        }
                    },
                    style = CrowdZeroTheme.typography.h2Bold
                )
                Text(
                    text = stringResource(R.string.detail_sub_header),
                    style = CrowdZeroTheme.typography.h3Regular,
                    color = CrowdZeroTheme.colors.gray700
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_pin),
                contentDescription = null
            )
        }
        when (weatherState) {
            is UiState.Empty -> {
                Timber.e("날씨 api 데이터 없음")
            }

            is UiState.Loading -> {
                LoadingIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            is UiState.Success -> {
                WeatherItem(
                    data = weatherState.data,
                    time = time
                )
            }

            is UiState.Failure -> {
                Timber.e("날씨 api 오류 : ${weatherState.msg}")
                WeatherItem(
                    data = WeatherEntity(
                        id = id,
                        areaNm = title,
                        skyStts = "정보 없음",
                        temp = 0,
                        pm25Index = "정보 없음",
                        pm10Index = "정보 없음",
                    ),
                    time = time
                )
            }
        }
        Spacer(modifier = Modifier.height(11.dp))
        NaverMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            properties = mapProperties,
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = location),
                icon = when (congestionState is UiState.Success) {
                    true -> when (congestionState.data.level) {
                        "여유" -> OverlayImage.fromResource(CongestionType.GOOD.icon)
                        "보통" -> OverlayImage.fromResource(CongestionType.NORMAL.icon)
                        "약간 혼잡" -> OverlayImage.fromResource(CongestionType.LITTLE_BAD.icon)
                        "혼잡" -> OverlayImage.fromResource(CongestionType.BAD.icon)
                        else -> OverlayImage.fromResource(CongestionType.UNKNOWN.icon)
                    }

                    false -> OverlayImage.fromResource(CongestionType.UNKNOWN.icon)
                },
                onClick = {
                    val cameraUpdate = CameraUpdate
                        .scrollAndZoomTo(location, 15.0)
                        .animate(CameraAnimation.Easing)
                    cameraPositionState.move(cameraUpdate)
                    true
                }
            )
        }
        when (congestionState) {
            is UiState.Empty -> Timber.d("혼잡도 api 데이터 없음")
            is UiState.Loading -> LoadingIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            is UiState.Success -> CongestionItem(data = congestionState.data)
            is UiState.Failure -> {
                Timber.e("혼잡도 api 오류 : ${congestionState.msg}")
                CongestionItem(
                    data = CongestionEntity(
                        id = id,
                        name = title,
                        level = "모름",
                        message = stringResource(R.string.detail_congestion_failure),
                        min = 0,
                        max = 0,
                        time = time
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CrowdZeroAndroidTheme {
        DetailScreen()
    }
}

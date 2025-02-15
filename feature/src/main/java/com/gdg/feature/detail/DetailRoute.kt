package com.gdg.feature.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gdg.core.designsystem.component.bar.CongestionBar
import com.gdg.core.designsystem.component.chip.FineDustChip
import com.gdg.core.designsystem.component.indicator.LoadingIndicator
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.state.UiState
import com.gdg.core.type.CongestionType
import com.gdg.core.type.DustConditionType
import com.gdg.core.type.DustType
import com.gdg.core.type.LocationType
import com.gdg.core.util.TimeFormatter
import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.WeatherEntity
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
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.overlay.OverlayImage
import okhttp3.internal.immutableListOf
import timber.log.Timber


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
    val getWeatherState by detailViewModel.getWeatherState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        detailViewModel.getWeather(id)
    }

    DetailScreen(
        paddingValues = paddingValues,
        weatherState = getWeatherState,
        weatherEntity = detailViewModel.mockWeather,
        congestionEntity = detailViewModel.mockCongestion,
        mapProperties = mapProperties,
        mapUiSettings = mapUiSettings,
        cameraPositionState = cameraPositionState,
        title = stringResource(LocationType.extractTitleResource(id)),
        location = LocationType.extractLatLng(id)
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun DetailScreen(
    paddingValues: PaddingValues = PaddingValues(),
    weatherState: UiState<WeatherEntity> = UiState.Empty,
    weatherEntity: WeatherEntity,
    congestionEntity: CongestionEntity,
    mapProperties: MapProperties = MapProperties(),
    mapUiSettings: MapUiSettings = MapUiSettings(),
    cameraPositionState: CameraPositionState = CameraPositionState(),
    title: String = "",
    location: LatLng = LatLng(37.574187, 126.976882)
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
                LoadingIndicator()
            }

            is UiState.Success -> {
                WeatherItem(
                    data = weatherState.data,
                    time = congestionEntity.time
                )
            }

            is UiState.Failure -> {
                Timber.e("날씨 api 오류 : ${weatherState.msg}")
                WeatherItem(
                    data = weatherEntity,
                    time = congestionEntity.time
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
                icon = when (congestionEntity.level) {
                    "여유" -> OverlayImage.fromResource(CongestionType.GOOD.icon)
                    "보통" -> OverlayImage.fromResource(CongestionType.NORMAL.icon)
                    "약간 혼잡" -> OverlayImage.fromResource(CongestionType.LITTLE_BAD.icon)
                    else -> OverlayImage.fromResource(CongestionType.BAD.icon)
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
        CongestionItem(data = congestionEntity)
    }
}

@Composable
fun WeatherItem(
    data: WeatherEntity,
    time: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = immutableListOf(
                        CrowdZeroTheme.colors.blue100,
                        CrowdZeroTheme.colors.blue200
                    ),
                    startX = 0f,
                    endX = 500f
                ),
                shape = RectangleShape
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = TimeFormatter().weatherTimeFormat(time),
                style = CrowdZeroTheme.typography.c3Bold,
                color = CrowdZeroTheme.colors.white
            )
            Row(
                modifier = Modifier.padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_detail_marker),
                    contentDescription = null
                )
                Text(
                    text = stringResource(R.string.detail_weather_seoul, data.areaNm),
                    style = CrowdZeroTheme.typography.c3Regular,
                    color = CrowdZeroTheme.colors.white
                )
            }
            FineDustChip(
                dust = DustType.FINE,
                condition = when (data.pm25Index) {
                    "좋음" -> DustConditionType.GOOD
                    "보통" -> DustConditionType.NORMAL
                    "나쁨" -> DustConditionType.BAD
                    else -> DustConditionType.BAD
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            FineDustChip(
                dust = DustType.ULTRA_FINE,
                condition = when (data.pm10Index) {
                    "좋음" -> DustConditionType.GOOD
                    "보통" -> DustConditionType.NORMAL
                    "나쁨" -> DustConditionType.BAD
                    else -> DustConditionType.BAD
                }
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(end = 7.dp),
            text = stringResource(R.string.detail_weather_temperature, data.temp),
            style = CrowdZeroTheme.typography.h1Regular,
            color = CrowdZeroTheme.colors.white
        )
        Image(
            modifier = Modifier.size(90.dp),
            imageVector = when (data.skyStts) {
                "맑음" -> ImageVector.vectorResource(R.drawable.ic_sunny)
                "구름많음" -> ImageVector.vectorResource(R.drawable.ic_cloudy)
                "비" -> ImageVector.vectorResource(R.drawable.ic_rainy)
                "눈" -> ImageVector.vectorResource(R.drawable.ic_snowy)
                else -> ImageVector.vectorResource(R.drawable.ic_sunny_cloudy)
            },
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun CongestionItem(
    data: CongestionEntity
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = stringResource(R.string.detail_header_congestion),
            style = CrowdZeroTheme.typography.h2Bold,
            color = CrowdZeroTheme.colors.gray900
        )
        Text(
            modifier = Modifier.padding(top = 6.dp, bottom = 2.dp),
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray700)) {
                    append(stringResource(R.string.detail_sub_header_congestion))
                }
                withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.orange)) {
                    append(stringResource(R.string.detail_congestion_count, data.min, data.max))
                }
            },
            style = CrowdZeroTheme.typography.c1SemiBold
        )
        Text(
            text = data.message,
            style = CrowdZeroTheme.typography.c2Medium1,
            color = CrowdZeroTheme.colors.gray600
        )
    }
    Spacer(modifier = Modifier.padding(top = 13.dp))
    CongestionBar(
        congestionType = when (data.level) {
            "여유" -> CongestionType.GOOD
            "보통" -> CongestionType.NORMAL
            "약간 혼잡" -> CongestionType.LITTLE_BAD
            else -> CongestionType.BAD
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CrowdZeroAndroidTheme {
        val detailViewModel: DetailViewModel = hiltViewModel()
        DetailScreen(
            weatherEntity = detailViewModel.mockWeather,
            congestionEntity = detailViewModel.mockCongestion
        )
    }
}

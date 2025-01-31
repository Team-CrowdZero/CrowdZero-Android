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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.component.bar.CongestionBar
import com.gdg.core.designsystem.component.chip.FineDustChip
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.type.CongestionType
import com.gdg.core.type.DustConditionType
import com.gdg.core.type.DustType
import com.gdg.feature.R
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.NaverMap


@Composable
fun DetailRoute(
    detailViewModel: DetailViewModel = hiltViewModel(),
    id: Int,
    paddingValues: PaddingValues,
    navigateUp: () -> Unit
) {
    LaunchedEffect(key1 = detailViewModel.sideEffects) {
        detailViewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is DetailSideEffect.NavigateUp -> navigateUp()
            }
        }
    }

    DetailScreen(
        paddingValues = paddingValues
    )
}

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun DetailScreen(
    paddingValues: PaddingValues = PaddingValues()
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(scrollState)
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(bottom = 16.dp)
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
                            append("지금의 ")
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.green700)) {
                            append("광화문 광장")
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                            append("은")
                        }
                    },
                    style = CrowdZeroTheme.typography.h2Bold
                )
                Text(
                    text = "인구 혼잡도와 날씨 정보를 확인하세요",
                    style = CrowdZeroTheme.typography.h3Regular,
                    color = CrowdZeroTheme.colors.gray700
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_pin),
                contentDescription = null
            )
        }
        WeatherItem()
        Spacer(modifier = Modifier.height(11.dp))
        NaverMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Text(
                text = "실시간 인구",
                style = CrowdZeroTheme.typography.h2Bold,
                color = CrowdZeroTheme.colors.gray900
            )
            Text(
                modifier = Modifier.padding(top = 6.dp, bottom = 2.dp),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray700)) {
                        append("현재 실시간 인구: ")
                    }
                    withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.orange)) {
                        append("3800~4000명")
                    }
                },
                style = CrowdZeroTheme.typography.c1SemiBold
            )
            Text(
                text = "사람이 몰려있을 가능성이 낮고 붐빔은 거의 느껴지지 않아요\n도보 이동이 자유로워요",
                style = CrowdZeroTheme.typography.c2Medium1,
                color = CrowdZeroTheme.colors.gray600
            )
        }
        Spacer(modifier = Modifier.padding(top = 13.dp))
        CongestionBar(congestionType = CongestionType.LITTLE_BAD)
    }
}

@Composable
fun WeatherItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
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
                text = "2025년 1월 23일 (목)",
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
                    text = "광화문 광장, 서울",
                    style = CrowdZeroTheme.typography.c3Regular,
                    color = CrowdZeroTheme.colors.white
                )
            }
            FineDustChip(
                dust = DustType.FINE,
                condition = DustConditionType.GOOD
            )
            Spacer(modifier = Modifier.height(5.dp))
            FineDustChip(
                dust = DustType.ULTRAFINE,
                condition = DustConditionType.BAD
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.padding(end = 7.dp),
            text = "1°",
            style = CrowdZeroTheme.typography.h1Regular,
            color = CrowdZeroTheme.colors.white
        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_rainy),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    CrowdZeroAndroidTheme {
        DetailScreen()
    }
}

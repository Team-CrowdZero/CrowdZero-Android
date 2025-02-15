package com.gdg.feature.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.component.chip.FineDustChip
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.type.DustConditionType
import com.gdg.core.type.DustType
import com.gdg.core.util.TimeFormatter
import com.gdg.domain.entity.WeatherEntity
import com.gdg.feature.R
import okhttp3.internal.immutableListOf

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
                    else -> DustConditionType.UNKNOWN
                }
            )
            Spacer(modifier = Modifier.height(5.dp))
            FineDustChip(
                dust = DustType.ULTRA_FINE,
                condition = when (data.pm10Index) {
                    "좋음" -> DustConditionType.GOOD
                    "보통" -> DustConditionType.NORMAL
                    "나쁨" -> DustConditionType.BAD
                    else -> DustConditionType.UNKNOWN
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

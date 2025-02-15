package com.gdg.feature.map.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.zIndex
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.extension.noRippleClickable
import com.gdg.domain.entity.PlaceEntity
import com.gdg.feature.R

@Composable
fun PlaceInfoCard(
    place: PlaceEntity?,
    modifier: Modifier = Modifier,
    onButtonClick: (Long) -> Unit
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
                                "혼잡" -> CrowdZeroTheme.colors.red
                                else -> CrowdZeroTheme.colors.gray700
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

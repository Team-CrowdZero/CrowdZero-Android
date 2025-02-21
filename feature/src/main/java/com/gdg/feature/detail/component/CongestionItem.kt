package com.gdg.feature.detail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.component.bar.CongestionBar
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.type.CongestionType
import com.gdg.domain.entity.CongestionEntity
import com.gdg.feature.R

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
                withStyle(
                    style = SpanStyle(
                        color = when (data.level) {
                            "여유" -> CrowdZeroTheme.colors.green600
                            "보통" -> CrowdZeroTheme.colors.yellow
                            "약간 붐빔" -> CrowdZeroTheme.colors.orange
                            "붐빔" -> CrowdZeroTheme.colors.red
                            else -> CrowdZeroTheme.colors.gray700
                        }
                    )
                ) {
                    if (data.min != data.max) {
                        append(stringResource(R.string.detail_congestion_count, data.min, data.max))
                    } else {
                        append(stringResource(R.string.detail_congestion_count_min, data.min))
                    }
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
            "약간 붐빔" -> CongestionType.LITTLE_BAD
            "붐빔" -> CongestionType.BAD
            else -> CongestionType.UNKNOWN
        }
    )
}

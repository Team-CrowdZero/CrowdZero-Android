package com.gdg.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.type.DustConditionType
import com.gdg.core.type.DustType

@Composable
fun FineDustChip(
    dust: DustType,
    condition: DustConditionType
) {
    Row(
        modifier = Modifier
            .width(69.dp)
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = CrowdZeroTheme.colors.shadow
            )
            .background(
                color = CrowdZeroTheme.colors.white,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray700)) {
                    append(
                        when (dust) {
                            DustType.FINE -> "미세먼지"
                            DustType.ULTRAFINE -> "초미세먼지"
                        }
                    )
                    append(" | ")
                }
                withStyle(
                    style = SpanStyle(
                        color = when (condition) {
                            DustConditionType.GOOD -> CrowdZeroTheme.colors.green600
                            DustConditionType.NORMAL -> CrowdZeroTheme.colors.orange
                            DustConditionType.BAD -> CrowdZeroTheme.colors.red
                        }
                    )
                ) {
                    append(
                        when (condition) {
                            DustConditionType.GOOD -> "좋음"
                            DustConditionType.NORMAL -> "보통"
                            DustConditionType.BAD -> "나쁨"
                        }
                    )
                }
            },
            style = CrowdZeroTheme.typography.c5SemiBold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FineDustChipPreview() {
    CrowdZeroAndroidTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(CrowdZeroTheme.colors.white)
        ) {
            FineDustChip(
                dust = DustType.FINE,
                condition = DustConditionType.GOOD
            )
        }
    }
}

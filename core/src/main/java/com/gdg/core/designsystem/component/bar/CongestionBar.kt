package com.gdg.core.designsystem.component.bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.core.R
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.type.CongestionType
import okhttp3.internal.immutableListOf

@Composable
fun CongestionBar(
    congestionType: CongestionType
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .let {
                                if (index == congestionType.index) {
                                    it
                                } else {
                                    it.padding(start = 0.dp)
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (index == congestionType.index) {
                            Icon(
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(bottom = 9.dp),
                                imageVector = ImageVector.vectorResource(R.drawable.ic_detail_marker),
                                contentDescription = null,
                                tint = congestionType.color
                            )
                        }
                    }
                }
            }
        }
        ColoredDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        ) {
            val textList = immutableListOf("여유", "보통", "약간 혼잡", "혼잡")
            textList.forEachIndexed { index, text ->
                val isSelected = index == congestionType.index
                Text(
                    modifier = Modifier.weight(1f),
                    text = text,
                    style = if (isSelected) CrowdZeroTheme.typography.c4SemiBold else CrowdZeroTheme.typography.c4Regular,
                    color = if (isSelected) CrowdZeroTheme.colors.gray900 else CrowdZeroTheme.colors.gray600,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ColoredDivider() {
    val colors = immutableListOf(
        CrowdZeroTheme.colors.green600,
        CrowdZeroTheme.colors.yellow,
        CrowdZeroTheme.colors.orange,
        CrowdZeroTheme.colors.red
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CircleShape)
    ) {
        colors.forEach { color ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(color)
                    .height(5.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CongestionBarPreview() {
    CrowdZeroAndroidTheme {
        CongestionBar(congestionType = CongestionType.GOOD)
    }
}

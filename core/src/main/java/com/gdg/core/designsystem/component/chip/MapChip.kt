package com.gdg.core.designsystem.component.chip

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.extension.noRippleClickable
import com.gdg.core.type.LocationType

@Composable
fun MapChip(
    title: LocationType,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val textColor = if (isSelected) CrowdZeroTheme.colors.green700 else CrowdZeroTheme.colors.gray800
    val shadowColor = if (isSelected) CrowdZeroTheme.colors.green700 else CrowdZeroTheme.colors.gray600

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        // 아래쪽 칩 (그림자 역할)
        Box(
            modifier = Modifier
                .offset(x = 2.dp, y = 4.dp)
                .clip(RoundedCornerShape(50))
                .background(shadowColor)
                .matchParentSize()
        )

        // 위쪽 칩 (실제 버튼)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .background(Color.White)
                .noRippleClickable { onClick() }
                .padding(horizontal = 20.dp, vertical = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = title.title),
                color = textColor,
                style = CrowdZeroTheme.typography.c4JalnanGothic
            )
        }
    }
}





@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewMapChip() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        MapChip(
            title = LocationType.GANGNAM_STATION,
            isSelected = true,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewUnselectedMapChip() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        MapChip(
            title = LocationType.GWANGHWAMUN,
            isSelected = false,
            onClick = {}
        )
    }
}



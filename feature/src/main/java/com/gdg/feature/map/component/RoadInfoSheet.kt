package com.gdg.feature.map.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.domain.entity.RoadEntity
import com.gdg.feature.R

@Composable
fun RoadInfoSheet(road: RoadEntity?) {
    if (road == null) return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, bottom = 21.dp, end = 16.dp, top = 14.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.5.dp,
                    color = CrowdZeroTheme.colors.gray500,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 13.dp, vertical = 12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = stringResource(R.string.map_modal_header),
                    color = CrowdZeroTheme.colors.gray900,
                    style = CrowdZeroTheme.typography.h5Bold
                )
                Text(
                    text = stringResource(R.string.map_modal_generated_time, road.acdntOccrDt),
                    color = CrowdZeroTheme.colors.gray700,
                    style = CrowdZeroTheme.typography.c3Medium
                )
                Text(
                    text = stringResource(R.string.map_modal_complete_time, road.expClrDt),
                    color = CrowdZeroTheme.colors.gray700,
                    style = CrowdZeroTheme.typography.c3Medium
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                modifier = Modifier.size(35.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_ban_marker),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier.padding(start = 5.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp, bottom = 3.dp),
                text = stringResource(R.string.map_modal_description, road.acdntInfo),
                color = CrowdZeroTheme.colors.gray800,
                style = CrowdZeroTheme.typography.c3Medium,
                textAlign = TextAlign.Start,
                lineHeight = 13.sp
            )
            Text(
                text = stringResource(R.string.map_modal_update_time, road.acdntTime),
                color = CrowdZeroTheme.colors.gray700,
                style = CrowdZeroTheme.typography.c4Regular
            )
            Text(
                text = stringResource(R.string.map_modal_source),
                color = CrowdZeroTheme.colors.gray700,
                style = CrowdZeroTheme.typography.c4Regular,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RoadInfoSheetPreview() {
    CrowdZeroAndroidTheme {
        RoadInfoSheet(
            road = RoadEntity(
                areaId = 1,
                acdntY = 37.123456,
                acdntX = 127.123456,
                acdntInfo = "세종대로 (교보빌딩 → 광화문역2번출구) 집회 무대설치로 진행방향 전차로 통제(반대가변운영)",
                acdntOccrDt = "2021-09-01 12:00",
                expClrDt = "2021-09-01 13:00",
                acdntTime = "2021-09-01 12:00"
            )
        )
    }
}

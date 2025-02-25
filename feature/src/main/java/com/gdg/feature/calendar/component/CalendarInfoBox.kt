package com.gdg.feature.calendar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.extension.showIf
import com.gdg.domain.entity.ScheduleEntity
import com.gdg.feature.R

@Composable
fun CalendarInfoBox(data: ScheduleEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, CrowdZeroTheme.colors.gray500, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(CrowdZeroTheme.colors.white)
            .padding(dimensionResource(R.dimen.default_padding)),
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = data.duration.replace("\n", "  "),
                style = CrowdZeroTheme.typography.c4SemiBold,
                color = CrowdZeroTheme.colors.green600
            )
            Text(
                text = data.region,
                style = CrowdZeroTheme.typography.c4SemiBold,
                color = CrowdZeroTheme.colors.white,
                modifier = Modifier
                    .background(
                        color = CrowdZeroTheme.colors.green600,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(horizontal = 8.dp, vertical = 3.dp)
                    .showIf(data.region != "None")
            )
        }
        Text(
            text = data.location.replace("\n", " "),
            style = CrowdZeroTheme.typography.h5Bold,
            color = CrowdZeroTheme.colors.gray900
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = stringResource(R.string.calendar_people_reporting_title),
                style = CrowdZeroTheme.typography.c3Regular,
                color = CrowdZeroTheme.colors.gray600
            )
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(R.string.calendar_people_reporting, data.people),
                style = CrowdZeroTheme.typography.c3Regular,
                color = CrowdZeroTheme.colors.gray800
            )
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = stringResource(R.string.calendar_slash),
                style = CrowdZeroTheme.typography.c3Regular,
                color = CrowdZeroTheme.colors.gray600
            )
            Text(
                modifier = Modifier.padding(end = 4.dp),
                text = stringResource(R.string.calendar_jurisdiction),
                style = CrowdZeroTheme.typography.c3Regular,
                color = CrowdZeroTheme.colors.gray600
            )
            Text(
                text = data.jurisdiction.replace("\n", " "),
                style = CrowdZeroTheme.typography.c3Regular,
                color = CrowdZeroTheme.colors.gray800
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarInfoBoxPreview() {
    CrowdZeroAndroidTheme {
        CalendarInfoBox(
            ScheduleEntity(
                date = "2021-10-01",
                duration = "07:30 ~ 24:00",
                location = "두터교회 앞 인도 및 2개 차로",
                region = "한남동",
                people = "3000",
                jurisdiction = "용산"
            )
        )
    }
}

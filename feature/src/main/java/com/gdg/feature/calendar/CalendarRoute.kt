package com.gdg.feature.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.feature.R
import com.gdg.domain.entity.ScheduleEntity

@Composable
fun CalendarRoute(
    paddingValues: PaddingValues,
    calendarViewModel: CalendarViewModel = hiltViewModel()
) {
    val scheduleEntity by calendarViewModel.mockSchedule.collectAsState()

    CalendarScreen(
        paddingValues = paddingValues,
        scheduleEntity = scheduleEntity
    )
}

@Composable
fun CalendarScreen(
    scheduleEntity: ScheduleEntity,
    paddingValues: PaddingValues = PaddingValues()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .navigationBarsPadding()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                            append(stringResource(R.string.calender_header_1))
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.green700)) {
                            append(stringResource(R.string.calender_header_2))
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                            append(stringResource(R.string.calender_header_3))
                        }
                    },
                    style = CrowdZeroTheme.typography.h2Bold
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(R.string.calender_sub_header_1),
                    style = CrowdZeroTheme.typography.h3Regular,
                    color = CrowdZeroTheme.colors.gray700
                )
                Text(
                    text = stringResource(R.string.calender_sub_header_2),
                    style = CrowdZeroTheme.typography.h3Regular,
                    color = CrowdZeroTheme.colors.gray700
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Image(
                painter = painterResource(R.drawable.ic_alert),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
        }

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(CrowdZeroTheme.colors.gray500)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.calender_selected_date),
                style = CrowdZeroTheme.typography.h3Bold,
                color = CrowdZeroTheme.colors.gray800
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.calender_selected_date_2),
                style = CrowdZeroTheme.typography.h3Bold,
                color = CrowdZeroTheme.colors.green700
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        CalendarInfoBox(data = scheduleEntity)
    }
}

@Composable
fun CalendarInfoBox(
    data: ScheduleEntity
) {
    Box(
        modifier = Modifier
            .size(370.dp, 85.dp)
            .border(0.5.dp, CrowdZeroTheme.colors.gray500, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.calender_time, data.duration),
                style = CrowdZeroTheme.typography.c4SemiBold,
                color = CrowdZeroTheme.colors.green600
            )

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.location,
                    style = CrowdZeroTheme.typography.h5Bold,
                    color = CrowdZeroTheme.colors.gray900
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = data.region,
                    style = CrowdZeroTheme.typography.c4SemiBold,
                    color = CrowdZeroTheme.colors.gray600
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.calender_people_reporting_title),
                        style = CrowdZeroTheme.typography.c3Regular,
                        color = CrowdZeroTheme.colors.gray600
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = stringResource(R.string.calender_people_reporting, data.people,),
                        style = CrowdZeroTheme.typography.c3Regular,
                        color = CrowdZeroTheme.colors.gray800
                    )
                    Text(
                        text = stringResource(R.string.calender_slash),
                        style = CrowdZeroTheme.typography.c3Regular,
                        color = CrowdZeroTheme.colors.gray600
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.calender_jurisdiction),
                        style = CrowdZeroTheme.typography.c3Regular,
                        color = CrowdZeroTheme.colors.gray600
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = data.jurisdiction,
                        style = CrowdZeroTheme.typography.c3Regular,
                        color = CrowdZeroTheme.colors.gray800
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CrowdZeroAndroidTheme {
        CalendarScreen(
            scheduleEntity = ScheduleEntity(
                duration = "07:30 ~ 24:00",
                location = "두터교회 앞 인도 및 2개 차로",
                region = "한남동",
                people = "3000",
                jurisdiction = "용산"
            )
        )
    }
}

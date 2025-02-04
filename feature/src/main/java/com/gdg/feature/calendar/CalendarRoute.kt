package com.gdg.feature.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.gdg.core.designsystem.component.calendar.CalendarComponent
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.util.TimeFormatter
import com.gdg.domain.entity.ScheduleEntity
import com.gdg.feature.R
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarRoute(
    paddingValues: PaddingValues,
    calendarViewModel: CalendarViewModel = hiltViewModel(),
) {
    val scheduleList by calendarViewModel.scheduleList.collectAsState() // 전체 일정 리스트
    val selectedDate by calendarViewModel.selectedDate.collectAsState()

    // 선택한 날짜의 일정만 필터링
    val filteredSchedules = scheduleList.filter { it.date == selectedDate.toString() }

    CalendarScreen(paddingValues = paddingValues,
        scheduleList = filteredSchedules, // 선택한 날짜의 일정만 전달
        selectedDate = selectedDate,
        onDateSelected = { calendarViewModel.updateSelectedDate(it) })
}

@Composable
fun CalendarScreen(
    scheduleList: List<ScheduleEntity>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

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
                    }, style = CrowdZeroTheme.typography.h2Bold
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

            Image(
                painter = painterResource(R.drawable.ic_alert),
                contentDescription = null,
                modifier = Modifier.size(105.dp)
            )
        }

        // 캘린더 컴포넌트
        CalendarComponent(
            currentMonth = currentMonth,
            selectedDate = selectedDate,
            onMonthChange = { currentMonth = it },
            onDateSelected = onDateSelected
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 23.dp),
            thickness = 1.dp,
            color = CrowdZeroTheme.colors.gray500
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 선택한 날짜 표시
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 11.dp),
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                        append(selectedDate.format(TimeFormatter.dateFormatter))
                    }
                    append(" ")
                    withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.green700)) {
                        append(selectedDate.format(TimeFormatter.dayOfWeekFormatter))
                    }
                },
                style = CrowdZeroTheme.typography.h3Bold,
            )
            if (scheduleList.isEmpty()) {
                // 정보 없는 경우 메시지 표시
                Text(
                    text = "해당 날짜의 집회 정보가 없습니다",
                    style = CrowdZeroTheme.typography.h5Medium,
                    color = CrowdZeroTheme.colors.gray900,
                    modifier = Modifier.padding(top = 72.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(scheduleList) { schedule ->
                        CalendarInfoBox(data = schedule)
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarInfoBox(data: ScheduleEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(0.5.dp, CrowdZeroTheme.colors.gray500, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp)).background(CrowdZeroTheme.colors.white)
            .padding(16.dp)
    ) {
        Text(
            text = data.duration,
            style = CrowdZeroTheme.typography.c4SemiBold,
            color = CrowdZeroTheme.colors.green600
        )

        Spacer(modifier = Modifier.height(2.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
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
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.calender_people_reporting_title),
                    style = CrowdZeroTheme.typography.c3Regular,
                    color = CrowdZeroTheme.colors.gray600
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = data.people,
                    style = CrowdZeroTheme.typography.c3Regular,
                    color = CrowdZeroTheme.colors.gray800
                )
                Text(
                    text = stringResource(R.string.calender_people_reporting),
                    style = CrowdZeroTheme.typography.c3Regular,
                    color = CrowdZeroTheme.colors.gray800
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

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

// 월의 날짜 리스트 생성
fun generateDaysForMonth(month: YearMonth): List<LocalDate> {
    val firstDay = month.atDay(1)
    val lastDay = month.atEndOfMonth()
    return (1..lastDay.dayOfMonth).map { day -> firstDay.plusDays((day - 1).toLong()) }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CrowdZeroAndroidTheme {
        CalendarScreen(scheduleList = listOf(
            ScheduleEntity(
                date = LocalDate.now().toString(),
                duration = "07:30 ~ 24:00",
                location = "두터교회 앞 인도 및 2개 차로",
                region = "한남동",
                people = "3000",
                jurisdiction = "용산"
            )
        ), selectedDate = LocalDate.now(), onDateSelected = {})
    }
}
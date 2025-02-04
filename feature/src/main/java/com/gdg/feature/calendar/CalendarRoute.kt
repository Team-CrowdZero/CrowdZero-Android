package com.gdg.feature.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.feature.R
import com.gdg.domain.entity.ScheduleEntity
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun CalendarRoute(
    paddingValues: PaddingValues,
    calendarViewModel: CalendarViewModel = hiltViewModel()
) {
    val scheduleList by calendarViewModel.scheduleList.collectAsState() // 전체 일정 리스트
    val selectedDate by calendarViewModel.selectedDate.collectAsState()

    // 선택한 날짜의 일정만 필터링
    val filteredSchedules = scheduleList.filter { it.date == selectedDate }

    CalendarScreen(
        paddingValues = paddingValues,
        scheduleList = filteredSchedules, // 선택한 날짜의 일정만 전달
        selectedDate = selectedDate,
        onDateSelected = { calendarViewModel.updateSelectedDate(it) }
    )
}

@Composable
fun CalendarScreen(
    scheduleList: List<ScheduleEntity>,
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    paddingValues: PaddingValues = PaddingValues()
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

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(CrowdZeroTheme.colors.gray500)
        )

        Spacer(modifier = Modifier.height(25.dp))

        // 선택한 날짜 표시
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = selectedDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")),
                style = CrowdZeroTheme.typography.h3Bold,
                color = CrowdZeroTheme.colors.gray800
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = selectedDate.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale.KOREAN), // 한글 요일 표시
                style = CrowdZeroTheme.typography.h3Bold,
                color = CrowdZeroTheme.colors.green700
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        if (scheduleList.isEmpty()) {
            // 정보 없는 경우 메시지 표시
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = "해당 날짜의 집회 정보가 없습니다",
                style = CrowdZeroTheme.typography.h5Medium,
                color = CrowdZeroTheme.colors.gray900,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(scheduleList) { schedule ->
                    CalendarInfoBox(data = schedule)
                }
            }
        }
    }
}

@Composable
fun CalendarInfoBox(data: ScheduleEntity) {
    Box(
        modifier = Modifier
            .size(370.dp, 85.dp)
            .border(0.5.dp, CrowdZeroTheme.colors.gray500, shape = RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = data.duration,
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
                        text = data.people,
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
}

@Composable
fun CalendarComponent(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onMonthChange: (YearMonth) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
) {
    val days = remember(currentMonth) { generateDaysForMonth(currentMonth) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 상단: 년/월 & < > 버튼
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(
                    R.string.calender_component_date,
                    currentMonth.year.toString(),
                    currentMonth.monthValue.toString()
                ),
                style = CrowdZeroTheme.typography.h3Bold,
                color = CrowdZeroTheme.colors.green700
            )

            Spacer(modifier = Modifier.width(225.dp))

            Icon(
                painter = painterResource(id = R.drawable.calender_button_l),
                tint = CrowdZeroTheme.colors.green700,
                contentDescription = "Next Month",
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onMonthChange(currentMonth.minusMonths(1)) }
            )

            Spacer(modifier = Modifier.width(15.dp))

            Icon(
                painter = painterResource(id = R.drawable.calender_button_r),
                tint = CrowdZeroTheme.colors.green700,
                contentDescription = "Next Month",
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onMonthChange(currentMonth.plusMonths(1)) }
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(CrowdZeroTheme.colors.gray500)
        )

        Spacer(modifier = Modifier.height(15.dp))

        // 요일 헤더
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                Text(
                    text = day,
                    style = CrowdZeroTheme.typography.c2Medium2,
                    color = CrowdZeroTheme.colors.gray600
                )
            }
        }

        Spacer(modifier = Modifier.height(5.dp))

        // 달력 (7열 그리드)
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(days) { date ->
                val isSelected = date == selectedDate
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) CrowdZeroTheme.colors.green500 else Color.Transparent)
                        .clickable { onDateSelected(date) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = date.dayOfMonth.toString(),
                        style = CrowdZeroTheme.typography.c2Medium2,
                        color = CrowdZeroTheme.colors.gray800
                    )
                }
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
        CalendarScreen(
            scheduleList = listOf(
                ScheduleEntity(
                    date = LocalDate.now(),
                    duration = "07:30 ~ 24:00",
                    location = "두터교회 앞 인도 및 2개 차로",
                    region = "한남동",
                    people = "3000",
                    jurisdiction = "용산"
                )
            ),
            selectedDate = LocalDate.now(),
            onDateSelected = {}
        )
    }
}

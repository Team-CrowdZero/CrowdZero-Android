package com.gdg.feature.calendar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gdg.core.designsystem.component.calendar.CalendarComponent
import com.gdg.core.designsystem.component.indicator.LoadingIndicator
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.state.UiState
import com.gdg.core.util.TimeFormatter
import com.gdg.domain.entity.ScheduleEntity
import com.gdg.feature.R
import timber.log.Timber
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun CalendarRoute(
    paddingValues: PaddingValues,
    calendarViewModel: CalendarViewModel = hiltViewModel(),
) {
    val getScheduleState by calendarViewModel.getScheduleState.collectAsState()
    val selectedDate by calendarViewModel.selectedDate.collectAsState()

    LaunchedEffect(key1 = Unit) {
        val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        calendarViewModel.getAssembly(today)
    }

    CalendarScreen(
        paddingValues = paddingValues,
        getScheduleState = getScheduleState,
        selectedDate = selectedDate,
        onDateSelected = {
            calendarViewModel.updateSelectedDate(it)
            calendarViewModel.getAssembly(it.toString())
        }
    )
}

@Composable
fun CalendarScreen(
    getScheduleState: UiState<List<ScheduleEntity>>,
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
                            append(stringResource(R.string.calendar_header_1))
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.green700)) {
                            append(stringResource(R.string.calendar_header_2))
                        }
                        withStyle(style = SpanStyle(color = CrowdZeroTheme.colors.gray900)) {
                            append(stringResource(R.string.calendar_header_3))
                        }
                    },
                    style = CrowdZeroTheme.typography.h2Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(R.string.calendar_sub_header_1),
                    style = CrowdZeroTheme.typography.h3Regular,
                    color = CrowdZeroTheme.colors.gray700
                )
                Text(
                    text = stringResource(R.string.calendar_sub_header_2),
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
            when (getScheduleState) {
                is UiState.Empty -> {
                    Timber.e("일정 데이터 없음")
                }

                is UiState.Loading -> {
                    LoadingIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                is UiState.Success -> {
                    if (getScheduleState.data.isEmpty()) {
                        Text(
                            text = stringResource(R.string.calendar_no_info),
                            style = CrowdZeroTheme.typography.h5Medium,
                            color = CrowdZeroTheme.colors.gray900,
                            modifier = Modifier.padding(top = 72.dp)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            items(getScheduleState.data) { schedule ->
                                CalendarInfoBox(data = schedule)
                            }
                        }
                    }
                }

                is UiState.Failure -> {
                    Timber.e("일정 데이터 오류 : ${getScheduleState.msg}")
                    Text(
                        text = stringResource(R.string.calendar_not_connected),
                        style = CrowdZeroTheme.typography.h5Medium,
                        color = CrowdZeroTheme.colors.gray900,
                        modifier = Modifier.padding(top = 72.dp)
                    )
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
            .clip(RoundedCornerShape(15.dp))
            .background(CrowdZeroTheme.colors.white)
            .padding(dimensionResource(R.dimen.default_padding))
    ) {
        Text(
            text = data.duration,
            style = CrowdZeroTheme.typography.c4SemiBold,
            color = CrowdZeroTheme.colors.green600
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = data.location.chunked(45).joinToString("\n"), // 긴 문구 단어 단위로 줄바꿈
                style = CrowdZeroTheme.typography.h5Bold,
                color = CrowdZeroTheme.colors.gray900
            )
            Text(
                text = data.region,
                style = CrowdZeroTheme.typography.c4SemiBold,
                color = CrowdZeroTheme.colors.gray600
            )
        }
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
                text = data.jurisdiction,
                style = CrowdZeroTheme.typography.c3Regular,
                color = CrowdZeroTheme.colors.gray800
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview() {
    CrowdZeroAndroidTheme {
        CalendarScreen(
            getScheduleState = UiState.Success(
                listOf(
                    ScheduleEntity(
                        date = LocalDate.now().toString(),
                        duration = "07:30 ~ 24:00",
                        location = "두터교회 앞 인도 및 2개 차로",
                        region = "한남동",
                        people = "3000",
                        jurisdiction = "용산"
                    )
                )
            ),
            selectedDate = LocalDate.now(),
            onDateSelected = {}
        )
    }
}
package com.gdg.core.designsystem.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gdg.core.R
import com.gdg.core.designsystem.theme.CrowdZeroAndroidTheme
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.extension.noRippleClickable
import com.gdg.core.util.getDaysForMonth
import okhttp3.internal.immutableListOf
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun CalendarComponent(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onMonthChange: (YearMonth) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
) {
    val days = remember(currentMonth) { getDaysForMonth(currentMonth) }
    val firstDayOfWeek = DayOfWeek.SUNDAY // 일요일을 주의 시작으로 변경

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
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
                ), style = CrowdZeroTheme.typography.h3Bold, color = CrowdZeroTheme.colors.green700
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(painter = painterResource(id = R.drawable.calender_button_l),
                tint = CrowdZeroTheme.colors.green700,
                contentDescription = stringResource(R.string.previous_month),
                modifier = Modifier
                    .size(16.dp)
                    .noRippleClickable { onMonthChange(currentMonth.minusMonths(1)) }
            )
            Spacer(modifier = Modifier.width(15.dp))
            Icon(painter = painterResource(id = R.drawable.calender_button_r),
                tint = CrowdZeroTheme.colors.green700,
                contentDescription = stringResource(R.string.next_month),
                modifier = Modifier
                    .size(16.dp)
                    .noRippleClickable { onMonthChange(currentMonth.plusMonths(1)) }
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 30.dp),
            thickness = 1.dp,
            color = CrowdZeroTheme.colors.gray500
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val weekDays = immutableListOf(
                DayOfWeek.SUNDAY,
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY
            )
            weekDays.forEach { dayOfWeek ->
                val day = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
                Text(
                    text = day,
                    style = CrowdZeroTheme.typography.c2Medium2,
                    color = CrowdZeroTheme.colors.gray600
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth()
        ) {
            val emptyDays = (days.first().dayOfWeek.value % 7 - firstDayOfWeek.value + 7) % 7
            items(emptyDays) {
                Box(modifier = Modifier.aspectRatio(1f))
            }
            items(days) { date ->
                val isSelected = date == selectedDate
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .padding(5.dp)
                        .background(
                            shape = CircleShape,
                            color = if (isSelected) CrowdZeroTheme.colors.green500 else Color.Transparent
                        )
                        .noRippleClickable { onDateSelected(date) }
                ) {
                    Text(
                        text = date.dayOfMonth.toString(),
                        style = CrowdZeroTheme.typography.c2Medium2,
                        color = CrowdZeroTheme.colors.gray900,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarComponentPreview() {
    CrowdZeroAndroidTheme {
        CalendarComponent(
            currentMonth = YearMonth.now(),
            selectedDate = LocalDate.now(),
            onMonthChange = {},
            onDateSelected = {}
        )
    }
}
package com.gdg.core.designsystem.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gdg.core.designsystem.theme.CrowdZeroTheme
import com.gdg.core.R
import com.gdg.core.extension.noRippleClickable
import okhttp3.internal.immutableListOf
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarComponent(
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    onMonthChange: (YearMonth) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
) {
    val days = remember(currentMonth) { getDaysForMonth(currentMonth) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
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
                ), style = CrowdZeroTheme.typography.h3Bold, color = CrowdZeroTheme.colors.green700
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(id = R.drawable.calender_button_l),
                tint = CrowdZeroTheme.colors.green700,
                contentDescription = stringResource(R.string.previous_month),
                modifier = Modifier
                    .size(16.dp)
                    .noRippleClickable { onMonthChange(currentMonth.minusMonths(1)) }
            )

            Spacer(modifier = Modifier.width(15.dp))

            Icon(
                painter = painterResource(id = R.drawable.calender_button_r),
                tint = CrowdZeroTheme.colors.green700,
                contentDescription = stringResource(R.string.next_month),
                modifier = Modifier
                    .size(16.dp)
                    .noRippleClickable { onMonthChange(currentMonth.plusMonths(1)) }
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp, bottom = 15.dp),
            thickness = 1.dp,
            color = CrowdZeroTheme.colors.gray500
        )

        Spacer(modifier = Modifier.height(15.dp))

        // 요일 헤더
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {
            immutableListOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
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
            columns = GridCells.Fixed(7), modifier = Modifier.fillMaxWidth()
        ) {
            items(days) { date ->
                val isSelected = date == selectedDate
                Box(modifier = Modifier
                    .padding(5.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) CrowdZeroTheme.colors.green500 else Color.Transparent)
                    .noRippleClickable { onDateSelected(date) }) {
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
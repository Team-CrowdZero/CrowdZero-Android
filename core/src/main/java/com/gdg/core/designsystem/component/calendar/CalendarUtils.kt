package com.gdg.core.designsystem.component.calendar

import java.time.LocalDate
import java.time.YearMonth

// Month 정보를 받아서 해당 달의 날짜 리스트를 반환하는 함수
fun getDaysForMonth(month: YearMonth): List<LocalDate> {
    val firstDay = month.atDay(1)
    val lastDay = month.atEndOfMonth()
    return (1..lastDay.dayOfMonth).map { day -> firstDay.plusDays((day - 1).toLong()) }
}
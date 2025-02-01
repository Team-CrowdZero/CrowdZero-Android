package com.gdg.core.util

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeFormatter {
    fun weatherTimeFormat(time: String): String {
        val date = parseDateTime(time)
        val koreanDayOfWeek = mapOf(
            DayOfWeek.MONDAY to "월",
            DayOfWeek.TUESDAY to "화",
            DayOfWeek.WEDNESDAY to "수",
            DayOfWeek.THURSDAY to "목",
            DayOfWeek.FRIDAY to "금",
            DayOfWeek.SATURDAY to "토",
            DayOfWeek.SUNDAY to "일"
        )
        val dayOfWeek = koreanDayOfWeek[date.dayOfWeek] ?: ""
        return "${date.year}년 ${date.monthValue}월 ${date.dayOfMonth}일 (${dayOfWeek})"
    }

    private fun parseDateTime(dateTime: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        return LocalDateTime.parse(dateTime, formatter)
    }
}
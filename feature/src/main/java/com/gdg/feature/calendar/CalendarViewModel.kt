package com.gdg.feature.calendar

import androidx.lifecycle.ViewModel
import com.gdg.domain.entity.ScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {

    private val _scheduleList = MutableStateFlow(
        listOf(
            ScheduleEntity(
                date = "2025-02-10", // 날짜 추가
                duration = "07:30 ~ 24:00",
                location = "두터교회 앞 인도 및 2개 차로",
                region = "한남동",
                people = "3000",
                jurisdiction = "용산"
            ),
            ScheduleEntity(
                date = "2025-02-10", // 같은 날짜 다른 일정
                duration = "10:00 ~ 15:00",
                location = "구로구청 앞",
                region = "구로 5동",
                people = "1000",
                jurisdiction = "구로"
            ),
            ScheduleEntity(
                date = "2025-02-10", // 같은 날짜 다른 일정
                duration = "10:00 ~ 15:00",
                location = "송현공원 앞 인도",
                region = "송현동",
                people = "500",
                jurisdiction = "종로"
            ),
            ScheduleEntity(
                date = "2025-02-11", // 다른 날짜
                duration = "14:00 ~ 18:00",
                location = "청담고 앞",
                region = "반포동",
                people = "2000",
                jurisdiction = "서초"
            )
        )
    )
    val scheduleList: StateFlow<List<ScheduleEntity>> = _scheduleList

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    fun updateSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }
}
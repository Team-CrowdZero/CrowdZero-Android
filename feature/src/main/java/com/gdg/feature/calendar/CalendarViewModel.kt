package com.gdg.feature.calendar

import androidx.lifecycle.ViewModel
import com.gdg.domain.entity.ScheduleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor() : ViewModel() {

    private val _mockSchedule = MutableStateFlow(
        ScheduleEntity(
            duration = "07:30 ~ 24:00",
            location = "두터교회 앞 인도 및 2개 차로",
            region = "한남동",
            people = "3000",
            jurisdiction = "용산"
        )
    )
    val mockSchedule: StateFlow<ScheduleEntity> = _mockSchedule
}

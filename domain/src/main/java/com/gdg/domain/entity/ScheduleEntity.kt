package com.gdg.domain.entity

import java.time.LocalDate

data class ScheduleEntity(
    val date: LocalDate,  // 집회정보가 등록된 날짜 필드
    val duration: String,
    val location: String,
    val region: String,
    val people: String,
    val jurisdiction: String
)

package com.gdg.domain.entity

import java.time.LocalDate

data class ScheduleEntity(
    val date: String,
    val duration: String,
    val location: String,
    val region: String,
    val people: String,
    val jurisdiction: String
)

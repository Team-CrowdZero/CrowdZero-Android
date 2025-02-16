package com.gdg.data.mapper

import com.gdg.data.dto.response.AssemblyResponseDto
import com.gdg.domain.entity.ScheduleEntity

fun AssemblyResponseDto.toScheduleEntity() = ScheduleEntity(
    date = date,
    region = district,
    duration = assemblyTime,
    people = assemblyPopulation.toString(),
    location = assemblyPlace,
    jurisdiction = jurisdiction
)

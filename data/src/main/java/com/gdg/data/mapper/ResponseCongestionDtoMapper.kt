package com.gdg.data.mapper

import com.gdg.data.dto.response.CongestionResponseDto
import com.gdg.domain.entity.CongestionEntity

fun CongestionResponseDto.toCongestionEntity() = CongestionEntity(
    id = areaId.toLong(),
    name = areaNm,
    level = areaCongestLvl,
    message = areaCongestMsg,
    min = areaPpltnMin,
    max = areaPpltnMax,
    time = ppltnTime
)
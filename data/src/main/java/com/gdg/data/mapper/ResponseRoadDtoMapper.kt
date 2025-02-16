package com.gdg.data.mapper

import com.gdg.data.dto.response.RoadResponseDto
import com.gdg.domain.entity.RoadEntity

fun RoadResponseDto.toRoadEntity() = RoadEntity(
    areaId = id.toInt(),
    acdntOccrDt = acdntOccrDt,
    expClrDt = expClrDt,
    acdntInfo = acdntInfo,
    acdntX = acdntX,
    acdntY = acdntY,
    acdntTime = acdntTime
)

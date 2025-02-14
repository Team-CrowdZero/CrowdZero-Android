package com.gdg.data.mapper

import com.gdg.data.dto.response.WeatherResponseDto
import com.gdg.domain.entity.WeatherEntity

fun WeatherResponseDto.toWeatherEntity() = WeatherEntity(
    id, areaNm, skyStts, temp, pm25Index, pm10Index
)
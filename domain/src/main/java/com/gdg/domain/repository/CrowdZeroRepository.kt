package com.gdg.domain.repository

import com.gdg.domain.entity.WeatherEntity

interface CrowdZeroRepository {
    suspend fun getWeather(areaId: Long): Result<WeatherEntity>
}
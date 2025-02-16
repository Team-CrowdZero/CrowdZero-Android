package com.gdg.domain.repository

import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.ScheduleEntity
import com.gdg.domain.entity.WeatherEntity

interface CrowdZeroRepository {
    suspend fun getWeather(areaId: Long): Result<WeatherEntity>
    suspend fun getCongestion(areaId: Long): Result<CongestionEntity>
    suspend fun getAssembly(date: String): Result<ScheduleEntity>
}
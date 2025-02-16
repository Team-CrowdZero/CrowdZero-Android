package com.gdg.domain.repository

import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.ScheduleEntity
import com.gdg.domain.entity.WeatherEntity

interface CrowdZeroRepository {
    suspend fun getWeather(areaId: Int): Result<WeatherEntity>
    suspend fun getCongestion(areaId: Int): Result<CongestionEntity>
    suspend fun getAssembly(date: String): Result<ScheduleEntity>
}
package com.gdg.domain.repository

import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.RoadEntity
import com.gdg.domain.entity.ScheduleEntity
import com.gdg.domain.entity.WeatherEntity

interface CrowdZeroRepository {
    suspend fun getWeather(areaId: Int): Result<WeatherEntity>
    suspend fun getCongestion(areaId: Int): Result<CongestionEntity>
    suspend fun getRoad(areaId: Int): Result<List<RoadEntity>>
    suspend fun getAssembly(date: String): Result<List<ScheduleEntity>>
}
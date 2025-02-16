package com.gdg.data.repositoryimpl

import com.gdg.data.datasource.CrowdZeroDataSource
import com.gdg.data.mapper.toCongestionEntity
import com.gdg.data.mapper.toScheduleEntity
import com.gdg.data.mapper.toWeatherEntity
import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.ScheduleEntity
import com.gdg.domain.entity.WeatherEntity
import com.gdg.domain.repository.CrowdZeroRepository
import javax.inject.Inject

class CrowdZeroRepositoryImpl @Inject constructor(
    private val crowdZeroDataSource: CrowdZeroDataSource
) : CrowdZeroRepository {
    override suspend fun getWeather(areaId: Long): Result<WeatherEntity> {
        return runCatching {
            crowdZeroDataSource.getWeather(areaId).data?.toWeatherEntity()
                ?: throw Exception("Data is null")
        }
    }

    override suspend fun getCongestion(areaId: Long): Result<CongestionEntity> {
        return runCatching {
            crowdZeroDataSource.getCongestion(areaId).data?.toCongestionEntity()
                ?: throw Exception("Data is null")
        }
    }

    override suspend fun getAssembly(date: String): Result<ScheduleEntity> {
        return runCatching {
            crowdZeroDataSource.getAssembly(date).data?.toScheduleEntity()
                ?: throw Exception("Data is null")
        }
    }
}
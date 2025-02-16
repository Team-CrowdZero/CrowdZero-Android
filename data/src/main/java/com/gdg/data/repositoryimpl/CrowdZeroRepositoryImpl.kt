package com.gdg.data.repositoryimpl

import com.gdg.data.datasource.CrowdZeroDataSource
import com.gdg.data.mapper.toCongestionEntity
import com.gdg.data.mapper.toExampleEntity
import com.gdg.data.mapper.toWeatherEntity
import com.gdg.data.mapper.toRoadEntity
import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.RoadEntity
import com.gdg.domain.entity.WeatherEntity
import com.gdg.domain.repository.CrowdZeroRepository
import javax.inject.Inject

class CrowdZeroRepositoryImpl @Inject constructor(
    private val crowdZeroDataSource: CrowdZeroDataSource
) : CrowdZeroRepository {
    override suspend fun getWeather(areaId: Int): Result<WeatherEntity> {
        return runCatching {
            crowdZeroDataSource.getWeather(areaId).data?.toWeatherEntity()
                ?: throw Exception("Data is null")
        }
    }

    override suspend fun getCongestion(areaId: Int): Result<CongestionEntity> {
        return runCatching {
            crowdZeroDataSource.getCongestion(areaId).data?.toCongestionEntity()
                ?: throw Exception("Data is null")
        }
    }

    override suspend fun getRoad(areaId: Int): Result<List<RoadEntity>> {
        return runCatching {
            crowdZeroDataSource.getRoad(areaId).data?.map { it.toRoadEntity() } ?: emptyList()
        }
    }

}

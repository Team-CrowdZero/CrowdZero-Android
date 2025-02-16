package com.gdg.data.datasourceimpl

import com.gdg.data.datasource.CrowdZeroDataSource
import com.gdg.data.dto.BaseResponse
import com.gdg.data.dto.response.AssemblyResponseDto
import com.gdg.data.dto.response.CongestionResponseDto
import com.gdg.data.dto.response.WeatherResponseDto
import com.gdg.data.service.CrowdZeroService
import javax.inject.Inject

class CrowdZeroDataSourceImpl @Inject constructor(
    private val crowdZeroApiService: CrowdZeroService
) : CrowdZeroDataSource {
    override suspend fun getWeather(areaId: Long): BaseResponse<WeatherResponseDto> {
        return crowdZeroApiService.getWeather(areaId)
    }

    override suspend fun getCongestion(areaId: Long): BaseResponse<CongestionResponseDto> {
        return crowdZeroApiService.getCongestion(areaId)
    }

    override suspend fun getAssembly(date: String): BaseResponse<AssemblyResponseDto> {
        return crowdZeroApiService.getAssembly(date)
    }
}
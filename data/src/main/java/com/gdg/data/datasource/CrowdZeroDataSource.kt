package com.gdg.data.datasource

import com.gdg.data.dto.BaseResponse
import com.gdg.data.dto.response.AssemblyResponseDto
import com.gdg.data.dto.response.CongestionResponseDto
import com.gdg.data.dto.response.WeatherResponseDto

interface CrowdZeroDataSource {
    suspend fun getWeather(areaId: Long): BaseResponse<WeatherResponseDto>
    suspend fun getCongestion(areaId: Long): BaseResponse<CongestionResponseDto>
    suspend fun getAssembly(date: String): BaseResponse<AssemblyResponseDto>
}
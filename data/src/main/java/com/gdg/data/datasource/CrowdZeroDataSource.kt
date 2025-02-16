package com.gdg.data.datasource

import com.gdg.data.dto.BaseResponse
import com.gdg.data.dto.response.CongestionResponseDto
import com.gdg.data.dto.response.WeatherResponseDto

interface CrowdZeroDataSource {
    suspend fun getWeather(areaId: Int): BaseResponse<WeatherResponseDto>
    suspend fun getCongestion(areaId: Int): BaseResponse<CongestionResponseDto>
}
package com.gdg.data.datasource

import com.gdg.data.dto.BaseResponse
import com.gdg.data.dto.response.WeatherResponseDto

interface CrowdZeroDataSource {
    suspend fun getWeather(areaId: Long): BaseResponse<WeatherResponseDto>
}
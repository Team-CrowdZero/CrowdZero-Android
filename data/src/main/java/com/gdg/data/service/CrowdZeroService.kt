package com.gdg.data.service

import com.gdg.data.dto.BaseResponse
import com.gdg.data.dto.response.WeatherResponseDto
import com.gdg.data.service.ApiKeyStorage.API
import com.gdg.data.service.ApiKeyStorage.AREA_ID
import com.gdg.data.service.ApiKeyStorage.WEATHER
import retrofit2.http.GET
import retrofit2.http.Path

interface CrowdZeroService {
    @GET("/$API/$WEATHER/{$AREA_ID}")
    suspend fun getWeather(
        @Path(AREA_ID) areaId: Long
    ): BaseResponse<WeatherResponseDto>
}
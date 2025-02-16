package com.gdg.data.service

import com.gdg.data.dto.BaseResponse
import com.gdg.data.dto.response.AssemblyResponseDto
import com.gdg.data.dto.response.CongestionResponseDto
import com.gdg.data.dto.response.RoadResponseDto
import com.gdg.data.dto.response.WeatherResponseDto
import com.gdg.data.service.ApiKeyStorage.ACDNT
import com.gdg.data.service.ApiKeyStorage.API
import com.gdg.data.service.ApiKeyStorage.AREA_ID
import com.gdg.data.service.ApiKeyStorage.ASSEMBLY
import com.gdg.data.service.ApiKeyStorage.DATE
import com.gdg.data.service.ApiKeyStorage.PPLTN
import com.gdg.data.service.ApiKeyStorage.WEATHER
import retrofit2.http.GET
import retrofit2.http.Path

interface CrowdZeroService {
    @GET("/$API/$WEATHER/{$AREA_ID}")
    suspend fun getWeather(
        @Path(AREA_ID) areaId: Int
    ): BaseResponse<WeatherResponseDto>

    @GET("/$API/$PPLTN/{$AREA_ID}")
    suspend fun getCongestion(
        @Path(AREA_ID) areaId: Int
    ): BaseResponse<CongestionResponseDto>

    @GET("/$API/$ACDNT/{$AREA_ID}")
    suspend fun getRoad(
        @Path(AREA_ID) areaId: Int
    ): BaseResponse<List<RoadResponseDto>>
  
    @GET("/$API/$ASSEMBLY/{$DATE}")
    suspend fun getAssembly(
        @Path(DATE) date: String
    ): BaseResponse<List<AssemblyResponseDto>>
}

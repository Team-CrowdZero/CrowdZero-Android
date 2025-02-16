package com.gdg.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseDto(
    @SerialName("id") val id: Long,
    @SerialName("areaNm") val areaNm: String,
    @SerialName("skyStts") val skyStts: String,
    @SerialName("temp") val temp: Int,
    @SerialName("pm25Index") val pm25Index: String,
    @SerialName("pm10Index") val pm10Index: String,
    @SerialName("areaId") val areaId: Int
)
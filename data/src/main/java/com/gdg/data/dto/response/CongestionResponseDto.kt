package com.gdg.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CongestionResponseDto(
    @SerialName("areaId") val areaId: Int,
    @SerialName("areaNm") val areaNm: String,
    @SerialName("areaCongestLvl") val areaCongestLvl: String,
    @SerialName("areaCongestMsg") val areaCongestMsg: String,
    @SerialName("areaPpltnMin") val areaPpltnMin: Int,
    @SerialName("areaPpltnMax") val areaPpltnMax: Int,
    @SerialName("ppltnTime") val ppltnTime: String,
)
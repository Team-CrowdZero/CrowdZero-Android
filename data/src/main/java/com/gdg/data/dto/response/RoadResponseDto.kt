package com.gdg.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoadResponseDto(
    @SerialName("id") val id: Long,
    @SerialName("acdntOccrDt") val acdntOccrDt: String,
    @SerialName("expClrDt") val expClrDt: String,
    @SerialName("acdntInfo") val acdntInfo: String,
    @SerialName("acdntX") val acdntX: Double,
    @SerialName("acdntY") val acdntY: Double,
    @SerialName("acdntTime") val acdntTime: String,
    @SerialName("areaId") val areaId: Int
)
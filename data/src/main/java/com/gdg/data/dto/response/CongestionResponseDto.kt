package com.gdg.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CongestionResponseDto(
    val areaId: Int,
    val areaNm: String,
    val areaCongestLvl: String,
    val areaCongestMsg: String,
    val areaPpltnMin: Int,
    val areaPpltnMax: Int,
    val ppltnTime: String
)
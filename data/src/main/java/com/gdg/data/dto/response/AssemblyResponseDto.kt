package com.gdg.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AssemblyResponseDto(
    @SerialName("date") val date: String,
    @SerialName("assemblyTime") val assemblyTime: String,
    @SerialName("assemblyPlace") val assemblyPlace: String,
    @SerialName("assemblyPopulation") val assemblyPopulation: Int,
    @SerialName("jurisdiction") val jurisdiction: String,
    @SerialName("district") val district: String,
)


package com.gdg.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class AssemblyResponseDto(
    val date : String,
    val assemblyTime : String,
    val assemblyPlace : String,
    val assemblyPopulation : Int,
    val jurisdiction : String,
    val district : String
)


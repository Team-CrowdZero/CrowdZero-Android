package com.gdg.domain.entity

data class WeatherEntity(
    val id: Long,
    val areaNm: String,
    val skyStts: String,
    val temp: Int,
    val pm25Index: String,
    val pm10Index: String
)
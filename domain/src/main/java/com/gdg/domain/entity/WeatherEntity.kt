package com.gdg.domain.entity

data class WeatherEntity(
    val id: Int,
    val name: String,
    val status: String,
    val temperature: Int,
    val pm25: String,
    val pm10: String
)
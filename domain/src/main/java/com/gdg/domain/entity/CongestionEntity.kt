package com.gdg.domain.entity

data class CongestionEntity(
    val id: Int,
    val name: String,
    val level: String,
    val message: String,
    val min: Int,
    val max: Int,
    val time: String
)

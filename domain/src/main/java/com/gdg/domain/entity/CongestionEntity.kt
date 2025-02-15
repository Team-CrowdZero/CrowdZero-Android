package com.gdg.domain.entity

data class CongestionEntity(
    val id: Long,
    val name: String,
    val level: String,
    val message: String,
    val min: Int,
    val max: Int,
    val time: String
)

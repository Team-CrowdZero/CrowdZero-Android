package com.gdg.domain.entity

data class PlaceEntity(
    val id: Int,
    val name: String, //장소명
    val congestion: String, //인구혼잡도
    val min: Int,//인구 실시간 최소
    val max: Int // 인구 실시간 최대
)

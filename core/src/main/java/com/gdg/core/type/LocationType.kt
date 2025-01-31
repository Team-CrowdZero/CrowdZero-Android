package com.gdg.core.type

import com.naver.maps.geometry.LatLng

enum class LocationType(val id: Int, val text: String, val latLng: LatLng) {
    GANGNAM_STATION(1, "강남역", LatLng(37.49804946088347, 127.02772319928187)), // 강남역
    GWANGHWAMUN(2, "광화문 광장", LatLng(37.574187, 126.976882)), // 광화문 광장
    SAMGAKJI_STATION(3, "삼각지역", LatLng(37.535590177126885, 126.97405623149015)), // 삼각지역
    SEOUL_STATION(4, "서울역", LatLng(37.55474913412969, 126.97067705661028)), // 서울역
    YEOUIDO(5, "여의도", LatLng(37.52173393560175, 126.92437767000882)); // 여의도

    companion object {
        fun extractText(id: Int): String {
            return entries.find { it.id == id }?.text ?: GWANGHWAMUN.text
        }

        fun extractLatLng(id: Int): LatLng {
            return entries.find { it.id == id }?.latLng ?: GWANGHWAMUN.latLng
        }
    }
}

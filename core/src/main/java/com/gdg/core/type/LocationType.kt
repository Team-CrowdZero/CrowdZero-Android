package com.gdg.core.type

import com.naver.maps.geometry.LatLng

enum class LocationType(
    val id: Int,
    val text: String,
    val latLng: LatLng
) {
    GANGNAM_STATION(
        id = 1,
        text = "강남역",
        latLng = LatLng(37.49804946088347, 127.02772319928187)
    ), // 강남역
    GWANGHWAMUN(
        id = 2,
        text = "광화문 광장",
        latLng = LatLng(37.574187, 126.976882)
    ), // 광화문 광장
    SAMGAKJI_STATION(
        id = 3,
        text = "삼각지역",
        latLng = LatLng(37.535590177126885, 126.97405623149015)
    ), // 삼각지역
    SEOUL_STATION(
        id = 4,
        text = "서울역",
        latLng = LatLng(37.55474913412969, 126.97067705661028)
    ), // 서울역
    YEOUIDO(
        id = 5,
        text = "여의도",
        latLng = LatLng(37.52173393560175, 126.92437767000882)
    ); // 여의도

    companion object {
        fun extractText(id: Int): String {
            return entries.find { it.id == id }?.text ?: GWANGHWAMUN.text
        }

        fun extractLatLng(id: Int): LatLng {
            return entries.find { it.id == id }?.latLng ?: GWANGHWAMUN.latLng
        }
    }
}

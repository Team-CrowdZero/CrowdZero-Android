package com.gdg.core.type

import androidx.annotation.StringRes
import com.gdg.core.R
import com.naver.maps.geometry.LatLng

enum class LocationType(
    val id: Int,
    @StringRes val title: Int,
    val latLng: LatLng
) {
    GANGNAM_STATION(
        id = 1,
        title = R.string.location_gangnam_station,
        latLng = LatLng(37.49804946088347, 127.02772319928187)
    ), // 강남역
    GWANGHWAMUN(
        id = 2,
        title = R.string.location_gwanghwamun,
        latLng = LatLng(37.574187, 126.976882)
    ), // 광화문 광장
    SAMGAKJI_STATION(
        id = 3,
        title = R.string.location_samgakji_station,
        latLng = LatLng(37.535590177126885, 126.97405623149015)
    ), // 삼각지역
    SEOUL_STATION(
        id = 4,
        title = R.string.location_seoul_station,
        latLng = LatLng(37.55474913412969, 126.97067705661028)
    ), // 서울역
    YEOUIDO(
        id = 5,
        title = R.string.location_yeouido,
        latLng = LatLng(37.52173393560175, 126.92437767000882)
    ); // 여의도

    companion object {
        fun extractTitleResource(id: Int): Int {
            return entries.find { it.id == id }?.title ?: GWANGHWAMUN.title
        }

        fun extractLatLng(id: Int): LatLng {
            return entries.find { it.id == id }?.latLng ?: GWANGHWAMUN.latLng
        }
    }
}

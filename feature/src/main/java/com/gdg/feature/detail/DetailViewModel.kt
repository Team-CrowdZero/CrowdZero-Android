package com.gdg.feature.detail

import androidx.lifecycle.ViewModel
import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.WeatherEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    val mockWeather = WeatherEntity(
        id = 1,
        name = "강남역",
        status = "구름많음",
        temperature = -3,
        pm25 = "좋음",
        pm10 = "나쁨",
        time = "2025-02-01T10:00:00"
    )

    val mockCongestion = CongestionEntity(
        id = 1,
        name = "강남역",
        level = "보통",
        message = "사람이 몰려있을 가능성이 낮고 붐빔은 거의 느껴지지 않아요\n도보 이동이 자유로워요",
        min = 3800,
        max = 4000,
        time = "2025-02-01T10:00:00"
    )
}

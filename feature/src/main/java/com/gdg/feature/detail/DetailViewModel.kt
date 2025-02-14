package com.gdg.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.core.state.UiState
import com.gdg.domain.entity.CongestionEntity
import com.gdg.domain.entity.WeatherEntity
import com.gdg.domain.repository.CrowdZeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val crowdZeroRepository: CrowdZeroRepository
) : ViewModel() {
    private val _getWeatherState: MutableStateFlow<UiState<WeatherEntity>> =
        MutableStateFlow(UiState.Empty)
    val getWeatherState: StateFlow<UiState<WeatherEntity>> get() = _getWeatherState

    private val _getCongestionState: MutableStateFlow<UiState<CongestionEntity>> =
        MutableStateFlow(UiState.Empty)
    val getCongestionState: StateFlow<UiState<CongestionEntity>> get() = _getCongestionState

    fun getWeather(areaId: Long) {
        viewModelScope.launch {
            _getWeatherState.emit(UiState.Loading)
            crowdZeroRepository.getWeather(areaId).fold(
                onSuccess = {
                    _getWeatherState.emit(UiState.Success(it))
                },
                onFailure = {
                    _getWeatherState.emit(UiState.Failure(it.message.toString()))
                }
            )
        }
    }

    fun getCongestion(areaId: Long) {
        viewModelScope.launch {
            _getCongestionState.emit(UiState.Loading)
            crowdZeroRepository.getCongestion(areaId).fold(
                onSuccess = {
                    _getCongestionState.emit(UiState.Success(it))
                },
                onFailure = {
                    _getCongestionState.emit(UiState.Failure(it.message.toString()))
                }
            )
        }
    }

    val mockWeather = WeatherEntity(
        id = 1,
        areaNm = "강남역",
        skyStts = "구름많음",
        temp = -3,
        pm25Index = "좋음",
        pm10Index = "나쁨",
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

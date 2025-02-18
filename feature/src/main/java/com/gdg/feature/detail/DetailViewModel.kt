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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    fun getWeather(areaId: Int) {
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

    fun getCongestion(areaId: Int) {
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

    fun getCurrentTimeISO8601(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        return current.format(formatter)
    }
}

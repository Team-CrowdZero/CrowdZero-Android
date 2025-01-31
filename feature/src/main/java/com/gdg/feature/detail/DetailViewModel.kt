package com.gdg.feature.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.domain.entity.WeatherEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {
    private val _sideEffects: MutableSharedFlow<DetailSideEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<DetailSideEffect> get() = _sideEffects

    fun navigateUp() {
        viewModelScope.launch {
            _sideEffects.emit(DetailSideEffect.NavigateUp)
        }
    }

    val mockWeather = WeatherEntity(
        id = 1,
        name = "광화 광장",
        status = "구름많음",
        temperature = -3,
        pm25 = "보통",
        pm10 = "보통"
    )
}

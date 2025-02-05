package com.gdg.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.domain.entity.PlaceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {
    private val _sideEffects: MutableSharedFlow<MapSideEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<MapSideEffect> get() = _sideEffects

    private val _selectedPlace = MutableStateFlow<PlaceEntity?>(null)
    val selectedPlace: StateFlow<PlaceEntity?> get() = _selectedPlace

    // 목업 데이터
    private val places = listOf(
        PlaceEntity(1, "강남역", "여유", 100, 500),
        PlaceEntity(2, "광화문 광장", "혼잡", 300, 800),
        PlaceEntity(3, "삼각지역", "여유", 50, 300),
        PlaceEntity(4, "서울역", "약간 혼잡", 200, 600),
        PlaceEntity(5, "여의도", "혼잡", 500, 1000)
    )


    fun selectPlace(id: Int) {
        val place = places.find { it.id == id }
        _selectedPlace.update { place }
    }


    fun navigateToDetail(id: Int) {
        viewModelScope.launch {
            _sideEffects.emit(MapSideEffect.NavigateToDetail(id))
        }
    }
}

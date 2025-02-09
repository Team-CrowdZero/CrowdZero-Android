package com.gdg.feature.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.domain.entity.PlaceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {
    private val _sideEffects: MutableSharedFlow<MapSideEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<MapSideEffect> get() = _sideEffects

    val mockPlaces = listOf(
        PlaceEntity(1, "강남역", "보통", 100, 200),
        PlaceEntity(2, "광화문 광장", "보통", 100, 200),
        PlaceEntity(3, "서울역", "보통", 100, 200),
        PlaceEntity(4, "삼각지역", "보통", 100, 200),
        PlaceEntity(5, "여의도", "보통", 100, 200)
    )

    fun getPlaceEntity(id: Int): PlaceEntity? {
        return mockPlaces.find { it.id == id }
    }

    fun navigateToDetail(id: Int) {
        viewModelScope.launch {
            _sideEffects.emit(MapSideEffect.NavigateToDetail(id))
        }
    }
}

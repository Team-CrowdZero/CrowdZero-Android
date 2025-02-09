package com.gdg.feature.map

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.core.type.LocationType
import com.gdg.domain.entity.PlaceEntity
import com.gdg.domain.entity.RoadEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel() {
    private val _sideEffects: MutableSharedFlow<MapSideEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<MapSideEffect> get() = _sideEffects

    @Stable
    val locations = immutableListOf(
        LocationType.GANGNAM_STATION,
        LocationType.GWANGHWAMUN,
        LocationType.SAMGAKJI_STATION,
        LocationType.SEOUL_STATION,
        LocationType.YEOUIDO
    )

    fun getPlaceEntity(id: Int): PlaceEntity? {
        return mockPlaces.find { it.id == id }
    }

    fun navigateToDetail(id: Int) {
        viewModelScope.launch {
            _sideEffects.emit(MapSideEffect.NavigateToDetail(id))
        }
    }

    private val mockPlaces = listOf(
        PlaceEntity(1, "강남역", "보통", 100, 200),
        PlaceEntity(2, "광화문 광장", "보통", 100, 200),
        PlaceEntity(3, "삼각지역", "보통", 100, 200),
        PlaceEntity(4, "서울역", "보통", 100, 200),
        PlaceEntity(5, "여의도", "보통", 100, 200)
    )

    val mockRoadEntity = listOf(
        RoadEntity(1, "2021-09-01", "2021-09-02", "사고", 126.97719959199067, 37.57473917460146, "12:00"),
        RoadEntity(2, "2021-09-01", "2021-09-02", "사고", 126.97724062015716, 37.57196573522649, "12:00"),
        RoadEntity(3, "2021-09-01", "2021-09-02", "사고", 126.97681755577895, 37.56963658011575, "12:00")
    )
}

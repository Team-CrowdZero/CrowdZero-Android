package com.gdg.feature.map

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdg.core.state.UiState
import com.gdg.core.type.LocationType
import com.gdg.domain.entity.PlaceEntity
import com.gdg.domain.entity.RoadEntity
import com.gdg.domain.repository.CrowdZeroRepository
import com.gdg.feature.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val crowdZeroRepository: CrowdZeroRepository
) : ViewModel() {
    private val _getCongestionState: MutableStateFlow<UiState<PlaceEntity>> =
        MutableStateFlow(UiState.Empty)
    val getCongestionState: StateFlow<UiState<PlaceEntity>> get() = _getCongestionState

    private val _getRoadState: MutableStateFlow<UiState<List<RoadEntity>>> =
        MutableStateFlow(UiState.Empty)
    val getRoadState: StateFlow<UiState<List<RoadEntity>>> get() = _getRoadState

    private val _sideEffects: MutableSharedFlow<MapSideEffect> = MutableSharedFlow()
    val sideEffects: SharedFlow<MapSideEffect> get() = _sideEffects

    private val _roads = MutableStateFlow<List<RoadEntity>>(emptyList())
    val roads: StateFlow<List<RoadEntity>> get() = _roads

    fun getCongestion(areaId: Int) {
        viewModelScope.launch {
            _getCongestionState.emit(UiState.Loading)
            crowdZeroRepository.getCongestion(areaId).fold(
                onSuccess = {
                    val placeEntity = PlaceEntity(
                        id = it.id,
                        name = it.name,
                        congestion = it.level,
                        min = it.min,
                        max = it.max
                    )
                    _getCongestionState.emit(UiState.Success(placeEntity))
                },
                onFailure = {
                    _getCongestionState.emit(UiState.Failure(it.message.toString()))
                    _sideEffects.emit(MapSideEffect.ShowToast(R.string.server_failure))
                }
            )
        }
    }

    fun getRoads() {
        viewModelScope.launch {
            _getRoadState.emit(UiState.Loading)
            _roads.emit(emptyList())
            (1..5).forEach { areaId ->
                crowdZeroRepository.getRoad(areaId).fold(
                    onSuccess = {
                        _roads.emit(_roads.value + it)
                    },
                    onFailure = {
                        _getRoadState.emit(UiState.Failure(it.message.toString()))
                    }
                )
            }

            if (_roads.value.isNotEmpty()) {
                _getRoadState.emit(UiState.Success(_roads.value))
            } else {
                _getRoadState.emit(UiState.Failure("도로 정보를 가져오는 데 실패했습니다."))
            }
        }
    }

    @Stable
    val locations = immutableListOf(
        LocationType.GANGNAM_STATION,
        LocationType.GWANGHWAMUN,
        LocationType.SAMGAKJI_STATION,
        LocationType.SEOUL_STATION,
        LocationType.YEOUIDO
    )

    fun navigateToDetail(id: Int) {
        viewModelScope.launch {
            _sideEffects.emit(MapSideEffect.NavigateToDetail(id))
        }
    }
}
